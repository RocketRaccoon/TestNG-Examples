import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by andrey.smirnov on 11.01.2017.
 * http://stackoverflow.com/questions/41579710/ignore-failure-on-a-single-test
 */
public class IgnoreFailureOnASingleTest {

    int a = 0;

    @Test(retryAnalyzer = RetryAnalyzer.class, skipFailedInvocations = true)
    public void a() {
        a++;
        System.out.println(a);
        if (a < 4)
            assertTrue(false);
    }
}
