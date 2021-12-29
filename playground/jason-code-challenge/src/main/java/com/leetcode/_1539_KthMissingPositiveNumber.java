package com.leetcode;

/**
 * Leetcode 1539 - Kth Missing Positive Number
 * 
 * Description - https://leetcode.com/problems/kth-missing-positive-number/
 * 
 * Key - a[i] - i - 1 = missing number total
 * 
 * @author Jason Zhang
 *
 */
public class _1539_KthMissingPositiveNumber {
  public int findKthPositive(int[] arr, int k) {
    for (int i =0; i <= arr.length -1; i++) {
      if (arr[i] - i - 1 < k) continue;
      
      return i + k;
    }
    
    return 0;
  }
}
