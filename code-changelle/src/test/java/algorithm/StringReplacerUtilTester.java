package algorithm;

import org.jason.algorithm.StringReplacerUtil;
import org.junit.Assert;
import org.junit.Test;

public class StringReplacerUtilTester {

  @Test
  public void testStringReplace(){
    String result = StringReplacerUtil.replace("Hellow", 'w', 'd');
    Assert.assertEquals("Hellod", result);
  }
}
