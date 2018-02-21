package injection;

import org.aeonbits.owner.Config;

public interface TestConfig extends Config {

    @Key("param")
    String param();

    @Key("elseOne")
    String elseOne();

}
