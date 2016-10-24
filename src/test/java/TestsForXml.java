import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by andrey.smirnov on 24.10.2016.
 */
public class TestsForXml {

    @Test
    @Parameters({ "inputList" })
    public void test1(String inputList) {
        System.out.println("test1");
        List<String> data = Arrays.asList(inputList.split("\\s*,\\s*"));
        data.forEach(System.out::println);
    }

    @Test
    @Parameters({ "inputList" })
    public void test2(String inputList) {
        System.out.println("test2");
        List<String> data = Arrays.asList(inputList.split("\\s*,\\s*"));
        data.forEach(System.out::println);
    }

    @Test
    @Parameters({ "inputList" })
    public void test3(String inputList) {
        System.out.println("test3");
        List<String> data = Arrays.asList(inputList.split("\\s*,\\s*"));
        data.forEach(System.out::println);
    }

}
