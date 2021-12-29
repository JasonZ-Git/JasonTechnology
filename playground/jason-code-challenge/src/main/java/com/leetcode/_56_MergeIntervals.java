package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Leetcode 56 - Version Comparation
 * 
 * Description - https://leetcode.com/problems/merge-intervals/
 * 
 * Key - Sort it
 * 
 * @author Jason Zhang
 */
public class _56_MergeIntervals {

  public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
    LinkedList<int[]> merged = new LinkedList<>();

    for (int[] interval : intervals) {

      if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
        merged.add(interval);
      } else {
        merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
      }
    }
    return merged.toArray(new int[merged.size()][]);
  }
}
