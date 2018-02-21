package injection;

public class TestDriver {

    String param;
    String elseOne;

    public TestDriver(TestConfig config) {
        this.param = config.param();
        this.elseOne = config.elseOne();
    }

}
