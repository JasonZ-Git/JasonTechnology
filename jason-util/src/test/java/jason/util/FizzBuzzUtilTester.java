package jason.util;

import org.jason.util.algorithm.FizzBuzzUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FizzBuzzUtilTester {

  @Test
  public void testFizzBuzz() {
    Assertions.assertEquals("1", FizzBuzzUtil.getFizzBuzz(1));
    Assertions.assertEquals("Fizz", FizzBuzzUtil.getFizzBuzz(3));
    Assertions.assertEquals("Buzz", FizzBuzzUtil.getFizzBuzz(5));
    Assertions.assertEquals("FizzBuzz", FizzBuzzUtil.getFizzBuzz(15));
    Assertions.assertEquals("FizzBuzz", FizzBuzzUtil.getFizzBuzz(30));
    Assertions.assertEquals("Fizz", FizzBuzzUtil.getFizzBuzz(18));
    Assertions.assertEquals("19", FizzBuzzUtil.getFizzBuzz(19));
  }
}
