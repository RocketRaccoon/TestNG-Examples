import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * Created by andrey.smirnov on 03.03.2016.
 */
public class SmartBeforeMethod {

    @BeforeMethod()
    public void before(Method method, ITestContext context) {
        if (getTestAuthTypeFromGroup(method, context)
                ) {
            System.out.println("before1");
        } else
            System.out.println("before2");
    }

    private static boolean getTestAuthTypeFromGroup(Method method, ITestContext context) {
        return Stream.of(context.getAllTestMethods())
                .filter(test -> test.getMethodName().equals(method.getName()))
                .anyMatch(test -> hasAuthType(test, "g1"));
    }

    private static boolean hasAuthType(ITestNGMethod test, String authType) {
        return Stream.of(test.getGroups())
                .anyMatch(group -> group.equals(authType));
    }

    @Test(groups = "g1")
    public void test1() {
        System.out.println("test1");
    }

    @Test(groups = "g2")
    public void test2() {
        System.out.println("test2");
    }
}
