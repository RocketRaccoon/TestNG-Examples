package injection;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class TestDriverProvider implements Provider<TestDriver> {

    private TestConfig testConfig;

    @Inject
    public TestDriverProvider(TestConfig testConfig) {
        this.testConfig = testConfig;
    }

    @Override
    public TestDriver get() {
        return new TestDriver(testConfig);
    }

}
