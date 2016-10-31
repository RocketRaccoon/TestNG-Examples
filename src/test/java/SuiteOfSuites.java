import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

    /**
     * Created by andrey.smirnov on 31.10.2016.
     * http://stackoverflow.com/questions/40232078/xml-results-are-overridden-when-multiple-suites-are-executed
     */
public class SuiteOfSuites {


    public static void main(String args[]) {
        TestNG testng = new TestNG();
        List<XmlSuite> suites = new ArrayList<>();
        XmlSuite parentSuite = new XmlSuite();
        parentSuite.setName("Parent");
        suites.add(parentSuite);
        IntStream.rangeClosed(1, 5).forEach(
                i -> {
                    XmlSuite childSuite = new XmlSuite();
                    childSuite.setName("Child_" + i);
                    XmlTest test = new XmlTest(childSuite);
                    test.setName("Test_" + i);
                    XmlClass clazz = new XmlClass("SoftAssertComparingList");
                    test.setXmlClasses(Collections.singletonList(clazz));
                    childSuite.setParentSuite(parentSuite);
                    suites.add(childSuite);
                }
        );
        testng.setXmlSuites(suites);
        testng.run();
    }

}
