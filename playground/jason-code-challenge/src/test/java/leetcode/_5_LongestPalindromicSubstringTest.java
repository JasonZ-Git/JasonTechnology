package leetcode;

import org.junit.Assert;
import org.junit.Test;

public class _5_LongestPalindromicSubstringTest {
  
  @Test
  public void test() {
    _5_LongestPalindromicSubstring instance  = new _5_LongestPalindromicSubstring();
    String ans = instance.longestPalindrome("babad");
    
    Assert.assertEquals(ans, "bab");
  }
}
