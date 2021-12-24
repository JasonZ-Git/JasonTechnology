package leetcode;

import java.util.Arrays;

/**
 * Leetcode 410 - Split Array Largest Sum
 * 
 * Description - https://leetcode.com/problems/split-array-largest-sum/
 * 
 * Key - f[n,m] = max(f[k][m - 1], nums[k + 1] + ... + nums[i])
 * 
 * Note - non-aftereffect property - can try to use dynamic programming
 *      - There is one condition actually m<=n
 * 
 * @author Jason Zhang
 *
 */
public class SplitArrayLargestSum_410 {
  
  public int splitArray(int[] nums, int m) {
    int n = nums.length;
    int[][] f = new int[n + 1][m + 1];
    int[] sub = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= m; j++) {
        f[i][j] = Integer.MAX_VALUE;
      }
    }
    for (int i = 0; i < n; i++) {
      sub[i + 1] = sub[i] + nums[i];
    }

    f[0][0] = 0;
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m && j <= n; j++) {
        for (int k = 0; k < i; k++) {
          f[i][j] = Math.min(f[i][j], Math.max(f[k][j - 1], sub[i] - sub[k]));
        }
      }
    }
    return f[n][m];
  }
}
