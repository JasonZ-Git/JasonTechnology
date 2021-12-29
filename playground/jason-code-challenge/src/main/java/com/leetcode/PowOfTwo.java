package com.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * If a number is pow of 2
 * 
 * @author Jason Zhang
 */
public class PowOfTwo {

  private static Set<Integer> container = new HashSet<Integer>();
  static {
    int start = 1;
    for (int i = 0; i <= 31; i++) {
      container.add(start);
      start *= 2;
    }
  }

  /** Best Solution */
  public static boolean isPowerOfTwo_1(int n) {
    if (n <= 0)
      return false;

    return (n & (n - 1)) == 0;
  }

  /** Smart Solution */
  public static boolean isPowerOfTwo_2(int n) {
    if (n <= 0)
      return false;

    return container.contains(n);
  }
}
