package leetcode;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class _139_WordBreakTest {

  @Test
  public void test() {

    _139_WordBreak workBreak = new _139_WordBreak();
    
    boolean result = workBreak.wordBreak("leetcodeabc", Arrays.asList("leet","code","abc"));
    
    Assert.assertTrue(result);
  }
}
