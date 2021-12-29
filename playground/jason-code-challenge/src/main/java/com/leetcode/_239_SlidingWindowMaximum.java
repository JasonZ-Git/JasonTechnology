package com.leetcode;

/**
 * Leetcode 239 - Sliding Window Maximum
 * 
 * Description - https://leetcode.com/problems/sliding-window-maximum/
 * 
 * Key - Better way - sum[i+1] = sum[i] + nums[i+k] - num[i] 
 * 
 * @author Jason Zhang
 *
 */
public class _239_SlidingWindowMaximum {
  
  public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    if (n * k == 0)
      return new int[0];

    int[] output = new int[n - k + 1];
    for (int i = 0; i < n - k + 1; i++) {
      int max = Integer.MIN_VALUE;
      for (int j = i; j < i + k; j++)
        max = Math.max(max, nums[j]);
      output[i] = max;
    }
    return output;
  }
}
