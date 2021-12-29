package com.leetcode;

/**
 * Leetcode 829 - Consecutive Number Sum
 * 
 * Description - https://leetcode.com/problems/consecutive-numbers-sum
 * 
 * Key - N = (x+1) + (x+2) + ... +(x+k) = xk + k(k+1)/2 => x = N/k - (k+1)/2 >=0 => k <= sqrt(2N + 1/4) - 1/2
 * 
 * Note - This is a correct answer, the one in Leecode is wrong
 * 
 * @author Jason Zhang
 */
public class _829_ConsecutiveNumberSum {
  public int consecutiveNumbersSum(int n) {
    int upperLimit = (int) (Math.sqrt(2 * n + 0.25) - 0.5);

    int count = 0;
    for (int k = 1; k <= upperLimit; k++) {
      if (n >= k * (k + 1) && (2 * n - (k * k + k)) % (2 * k) == 0) {
        count++;
      }
    }

    return count;
  }
}
