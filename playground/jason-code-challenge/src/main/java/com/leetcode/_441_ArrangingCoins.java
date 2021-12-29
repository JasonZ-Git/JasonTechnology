package com.leetcode;

/**
 * Leetcode 441 - Arranging coins
 * 
 * Description - https://leetcode.com/problems/arranging-coins
 * 
 * Key - formula - 1 + 2 + 3 + ... + n = n * (n+1)/2
 * 
 * @author Jason Zhang
 *
 */
public class _441_ArrangingCoins {

  public int arrangeCoins(int n) {

    for (int i = 0; i <= n; i++) {
      int lowBound = i * (i + 1) / 2;
      int highBound = (i + 1) * (i + 2) / 2;
      if (n >= lowBound && n < highBound)
        return i;
    }

    return -1;
  }


  public int arrangeCoins_(int n) {
    return (int) (Math.sqrt(2 * (long) n + 0.25) - 0.5);
  }
}
