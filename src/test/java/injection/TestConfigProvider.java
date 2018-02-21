package injection;

import com.google.inject.Provider;
import org.aeonbits.owner.ConfigFactory;

public class TestConfigProvider implements Provider<TestConfig> {

    @Override
    public TestConfig get() {
        return ConfigFactory.create(TestConfig.class);
    }
}
