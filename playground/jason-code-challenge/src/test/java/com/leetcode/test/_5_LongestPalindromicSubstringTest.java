package com.leetcode.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.leetcode._5_LongestPalindromicSubstring;

public class _5_LongestPalindromicSubstringTest {

  @Test
  public void test() {
    _5_LongestPalindromicSubstring instance = new _5_LongestPalindromicSubstring();
    String ans = instance.longestPalindrome("babad");

    Assertions.assertEquals(ans, "bab");
  }
}
