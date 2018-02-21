package injection;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ComponentUnderTestProvider implements Provider<ComponentUnderTest> {

    private TestDriver testDriver;

    @Inject
    public ComponentUnderTestProvider(TestDriver testDriver) {
        this.testDriver = testDriver;
    }

    @Override
    public ComponentUnderTest get() {
        return new ComponentUnderTest(testDriver);
    }
}
