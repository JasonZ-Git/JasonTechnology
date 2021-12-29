package com.leetcode.test;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.leetcode._139_WordBreak;

public class _139_WordBreakTest {

  @Test
  public void test() {

    _139_WordBreak workBreak = new _139_WordBreak();

    boolean result = workBreak.wordBreak("leetcodeabc", Arrays.asList("leet", "code", "abc"));

    Assertions.assertTrue(result);
  }
}
