import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Objects;

/**
 * Created by andrey.smirnov on 11.01.2017.
 */
public class RetryAnalyzer implements IRetryAnalyzer {
    @Override
    public boolean retry(ITestResult result) {
        int count = result.getMethod().getCurrentInvocationCount();
        if (count < 2) {
            return true;
        }
        else if (count == 2)
            {
                return false;
            }
        else return false;
    }
}
