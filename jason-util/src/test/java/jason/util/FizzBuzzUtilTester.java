package jason.util;

import org.jason.util.algorithm.FizzBuzzUtil;
import org.junit.Test;

import junit.framework.Assert;

public class FizzBuzzUtilTester {

    @Test
    public void testFizzBuzz() {
        Assert.assertEquals("1", FizzBuzzUtil.getFizzBuzz(1));
        Assert.assertEquals("Fizz", FizzBuzzUtil.getFizzBuzz(3));
        Assert.assertEquals("Buzz", FizzBuzzUtil.getFizzBuzz(5));
        Assert.assertEquals("FizzBuzz", FizzBuzzUtil.getFizzBuzz(15));
        Assert.assertEquals("FizzBuzz", FizzBuzzUtil.getFizzBuzz(30));
        Assert.assertEquals("Fizz", FizzBuzzUtil.getFizzBuzz(18));
        Assert.assertEquals("19", FizzBuzzUtil.getFizzBuzz(19));
    }
}
