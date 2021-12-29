package com.leetcode;

import java.util.Arrays;

/**
 * Leetcode 1326 - Minimum taps to water a garden
 * 
 * Descritpion - https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/
 * 
 * Key - This is a greedy algorithm - it is not perfect, just pesudo - TODO More
 * 
 * @author Jason Zhang
 *
 */
public class _1326_MinTapToWaterGarden {
  public int minTaps(int n, int[] ranges) {
    int[][] rarr = new int[ranges.length][2];
    for (int i = 0; i < ranges.length; i++) {
      rarr[i][0] = (i - ranges[i]) >= 0 ? i - ranges[i] : 0;
      rarr[i][1] = (i + ranges[i]) <= n ? i + ranges[i] : n;
    }
    
    Arrays.sort(rarr, (a, b) -> Integer.compare(a[0], b[0]));

    int endAt = 0;
    int tapCount = 0;
    while (endAt != n) {
      int maxEndAt = -1;
      for (int i = 0; i < rarr.length && rarr[i][0] <= endAt; i++) {
        maxEndAt = Math.max(maxEndAt, rarr[i][1]);
      }
      
      if (maxEndAt == -1 || maxEndAt == endAt)
        return -1;
      
      tapCount++;
      endAt = maxEndAt;
    }

    return tapCount;
  }
}
