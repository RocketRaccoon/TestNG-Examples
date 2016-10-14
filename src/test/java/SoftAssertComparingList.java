import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
     * Created by andrey.smirnov on 12.10.2016.
     * http://stackoverflow.com/questions/39985123/how-to-see-all-assertionerror-messages-in-testng-soft-assert-comparing-list-str
     */
public class SoftAssertComparingList {

    final static List<String> a = Arrays.asList("A", "AA", "AAA", "B");
    final static List<String> b = Arrays.asList("B", "AA", "AAA", "A", "C", "D");



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
        Iterator<String> actIt = a.iterator();
        Iterator<String> expIt = b.iterator();
        int i = -1;
        while(actIt.hasNext() && expIt.hasNext()) {
            i++;
            softAssert.assertEquals(actIt.next(), expIt.next(), "Lists differ at element [" + i + "]");
        }
        if (actIt.hasNext() || expIt.hasNext()) {
            boolean isAct = actIt.hasNext();
            String msg = isAct ?  "Actual" : "Expected";
            msg = msg + " list have additional members: ";
            List<String> elements = isAct ? getElementsAsList(actIt) : getElementsAsList(expIt);
            msg = msg + String.join(", ", elements);
            softAssert.fail(msg);
        }
        softAssert.assertAll();
    }

    private List<String> getElementsAsList(Iterator<String> itr) {
        List<String> elements = new ArrayList<>();
        while (itr.hasNext()) elements.add("[" + itr.next() + "]");
        return elements;
    }

}
