import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
     * Created by andrey.smirnov on 12.10.2016.
     * http://stackoverflow.com/questions/39985123/how-to-see-all-assertionerror-messages-in-testng-soft-assert-comparing-list-str
     */
public class SoftAssertComparingList {

    final static List<String> a = Arrays.asList("A", "AA", "AAA", "B");
    final static List<String> b = Arrays.asList("B", "AA", "AAA", "A");



    @Test
    public void testHardAssert() {
        Assert.assertEquals(a,b, "a & b");
    }

    @Test
    public void testSoftAssert() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(a,b, "a & b");
        softAssert.assertAll();
    }

    @Test
    public void testIteratorAndSoftAssert() {
        SoftAssert softAssert = new SoftAssert();
        Iterator<?> actIt = a.iterator();
        Iterator<?> expIt = b.iterator();
        int i = -1;
        while(actIt.hasNext() && expIt.hasNext()) {
            i++;
            softAssert.assertEquals(actIt.next(), expIt.next(), "Lists differ at element [" + i + "]");
        }
        softAssert.assertAll();
    }


}
