import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

    public class DataProviderPerMethod {

        @DataProvider(name = "provider")
        public Object[][] provider(Method method) {
            List<TestData> options = Arrays.asList(method.getAnnotation(TestDataOptions.class).value());
            int optionsSize = options.size();
            int optionLength = Objects.requireNonNull(options.get(0)).value().length;
            Object[][] result = new Object[optionsSize][optionLength];
            IntStream.range(0, optionsSize).forEach(i -> result[i] = options.get(i).value());
            return result;
        }

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @Repeatable(TestDataOptions.class)
        public @interface TestData {
            String[] value();
        }

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface TestDataOptions {
            TestData[] value();
        }


        @TestDataOptions({
                @TestData({"1", "string"}),
                @TestData({"2", "else one string"})
        })
        @Test(dataProvider = "provider")
        public void verifyTestData(String first, String second) {
            System.out.println(first);
            System.out.println(second);
        }

    }
