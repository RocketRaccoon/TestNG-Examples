package data;

import com.jamesmurty.utils.XMLBuilder2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.xml.transform.OutputKeys;
import java.io.*;
import java.util.*;

import static javax.xml.transform.OutputKeys.*;
import static javax.xml.transform.OutputKeys.INDENT;

/**
     * Created by andrey.smirnov on 24.10.2016.
     */
public class XmlFromXlsx {

    private static final String TEST_DATA_XLSX = "build/resources/test/test_data.xlsx";
    private static final String NAME = "name";
    private static final int FIRST_DATA_COL = 6;
    private static final int LAST_DATA_COL = 8;
    private static final int IS_ENABLED = 2;
    private static final int TESTNG_METHOD = 4;
    private static final int DATASET_NAME = 3;
    private static final String VALUE = "value";
    private static final int TEST_NAME = 0;

    public static void main(String arg[]) {
        try {
            extractTestData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extractTestData() throws IOException {
        File testData = new File(TEST_DATA_XLSX);
        FileInputStream fis = new FileInputStream(testData);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = Objects.requireNonNull(workbook.getSheetAt(0));
        Map<String, List<String>> dataSets = new HashMap<>();

        for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if(Objects.nonNull(row)) {
                //assume that each test has unique data set
                dataSets.put(row.getCell(DATASET_NAME).getStringCellValue(), new ArrayList<>());
                for (int colIndex = FIRST_DATA_COL; colIndex <= LAST_DATA_COL; colIndex++) {
                    Cell cell = row.getCell(colIndex);
                    if(Objects.nonNull(cell)) {
                        dataSets.get(row.getCell(DATASET_NAME).getStringCellValue()).add(cell.getStringCellValue());
                    }
                }
            }
        }

        Iterator<Row> rowIterator = sheet.rowIterator();
        //Skip row with headers
        if (rowIterator.hasNext())
            rowIterator.next();

        XMLBuilder2 builder = XMLBuilder2.create("suite").a(NAME, "XLSX to XML test");
        while (rowIterator.hasNext()) {

            Row row = rowIterator.next();

            StringBuilder sb = new StringBuilder();
            for (String s : dataSets.get(row.getCell(DATASET_NAME).getStringCellValue())) {
                sb.append(s);
                sb.append(",");
            }
            String dataSetAsString = sb.deleteCharAt(sb.length()-1).toString();
            builder.e("test")
                    .a("enabled", String.valueOf(row.getCell(IS_ENABLED).getStringCellValue().equals("Y")))
                    .a(NAME, row.getCell(TEST_NAME).getStringCellValue())
                    .e("classes")
                        .e("class").a(NAME, "TestsForXml").e("methods")
                        .e("parameter").a(NAME, "inputList").a(VALUE, dataSetAsString).up()
                        .e("include")
                        .a(NAME, row.getCell(TESTNG_METHOD).getStringCellValue())
                    .up(3);

        }

        PrintWriter writer = new PrintWriter(new FileOutputStream("build/resources/test/generated_testng.xml"));
        Properties outputProperties = new Properties();
        outputProperties.put(INDENT, "yes");
        outputProperties.put(OutputKeys.DOCTYPE_SYSTEM, "http://testng.org/testng-1.0.dtd");
        outputProperties.put("{http://xml.apache.org/xslt}indent-amount", "2");
        builder.toWriter(writer, outputProperties);
    }

}
