package com.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Leetcode 128 - Longest Consecutive Sequence
 * 
 * Description - https://leetcode.com/problems/longest-consecutive-sequence/
 * 
 * Key - Sort or use HashSet
 * 
 * @author Jason Zhang
 *
 */
public class _128_LongestConsecutiveSequence {
  public int longestConsecutive(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }

    Arrays.sort(nums);

    int longestStreak = 1;
    int currentStreak = 1;

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] != nums[i - 1]) {
        if (nums[i] == nums[i - 1] + 1) {
          currentStreak += 1;
        } else {
          longestStreak = Math.max(longestStreak, currentStreak);
          currentStreak = 1;
        }
      }
    }

    return Math.max(longestStreak, currentStreak);
  }

  public int longestConsecutive_best(int[] nums) {
    Set<Integer> num_set = new HashSet<Integer>();
    for (int num : nums) {
      num_set.add(num);
    }

    int longestStreak = 0;

    for (int num : num_set) {
      if (!num_set.contains(num - 1)) {
        int currentNum = num;
        int currentStreak = 1;

        while (num_set.contains(currentNum + 1)) {
          currentNum += 1;
          currentStreak += 1;
        }

        longestStreak = Math.max(longestStreak, currentStreak);
      }
    }

    return longestStreak;
  }
}
