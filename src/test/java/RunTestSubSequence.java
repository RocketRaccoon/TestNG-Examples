import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
     * Created by andrey.smirnov on 04.10.2016.
     * http://stackoverflow.com/questions/39684367/repeating-of-tests-subsequence-using-testng/39695279#comment66708185_39695279
     */

public class RunTestSubSequence {

    @BeforeTest(alwaysRun = true)
    public void before(ITestContext c) {
        System.out.println(c.getName());
    }

    @Test
    public void a() {System.out.println("a");}

    @Test
    public void b() {System.out.println("b");}

    @Test(groups = {"Begins_from_c"})
    public void c() {System.out.println("c");}

    @Test(groups = {"Begins_from_c"})
    public void d() {System.out.println("d");}

    @Test(groups = {"Begins_from_c"})
    public void e() {System.out.println("e");}

}
