package injection;

import com.google.inject.Inject;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Guice(modules = {TestModule.class})
public class SimpleTest {

    @Inject
    ComponentUnderTest component;

    @Test
    public void sampleTest() {
        System.out.println(component.getParamToTest());
        System.out.println(component.param);
        System.out.println(component.elseone);
    }

}
