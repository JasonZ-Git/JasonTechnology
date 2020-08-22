package algorithm;

import org.jason.algorithm.StringReverseUtil;
import org.junit.Assert;
import org.junit.Test;


public class StringReverseUtilTester {

    @Test
    public void tester() {
        Assert.assertEquals(StringReverseUtil.reverse("hello"), "olleh");
    }
}
