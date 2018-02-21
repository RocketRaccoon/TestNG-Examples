package injection;

public class ComponentUnderTest {

    private int paramToTest = 42;
    String param;
    String elseone;

    public ComponentUnderTest(TestDriver testDriver) {
        this.param = testDriver.param;
        this.elseone = testDriver.elseOne;
    }

    public int getParamToTest() {
        return paramToTest;
    }
}
