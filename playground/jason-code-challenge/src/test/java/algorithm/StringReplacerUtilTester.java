package algorithm;

import org.jason.algorithm.StringReplacerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringReplacerUtilTester {

  @Test
  public void testStringReplace() {
    String result = StringReplacerUtil.replace("Hellow", 'w', 'd');
    Assertions.assertEquals("Hellod", result);
  }
}
