package injection;

import com.google.inject.Binder;
import com.google.inject.Module;

public class TestModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(TestConfig.class).toProvider(TestConfigProvider.class);
        binder.bind(TestDriver.class).toProvider(TestDriverProvider.class);
        binder.bind(ComponentUnderTest.class).toProvider(ComponentUnderTestProvider.class);
    }
}
