import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Objects;

import static org.testng.Assert.assertTrue;

/**
 * Created by andrey.smirnov on 11.01.2017.
 * http://stackoverflow.com/questions/41579710/ignore-failure-on-a-single-test
 */
public class IgnoreFailureOnASingleTest {

    int a = 0;

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void a() {
        a++;
        System.out.println("a" + a);
        if (a < 3)
            assertTrue(false);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void b() {
        System.out.println("b");
    }

    @Test()
    public void c() {
        System.out.println("c");
    }

}
