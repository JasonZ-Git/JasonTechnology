package com.leetcode;

/**
 * Leetcode 1010 - Pairs of Songs With Total Durations Divisible by 60
 * 
 * Description - https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/solution/
 * 
 * Key - (a+b) % 60 => a%60 == 0 or b%60 == 0 or a%60 + b%60 == 60
 * 
 * @author Jason Zhang
 *
 */
public class _1010_PairOfSongsTotalDurationDivisiableBy60 {
  public int numPairsDivisibleBy60(int[] time) {
    int remainders[] = new int[60];
    int count = 0;
    for (int t : time) {
      if (t % 60 == 0) {
        count += remainders[0];
      } else {
        count += remainders[60 - t % 60];
      }
      remainders[t % 60]++;
    }
    return count;
  }

  // Best
  public int numPairsDivisibleBy60_2(int[] time) {
    int remainders[] = new int[60];
    int count = 0;
    for (int t : time) {
      int tMode = (60 - t % 60) % 60;
      count += remainders[tMode];
      remainders[t % 60]++;
    }
    return count;
  }
}
