package leetcode;

/**
 * Leetcode 239 - Sliding Window Maximum
 * 
 * Description - https://leetcode.com/problems/sliding-window-maximum/
 * 
 * @author Jason Zhang
 *
 */
public class SlidingWindowMaximum_239 {
  
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
