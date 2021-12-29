package com.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * Leetcode 1443 - Minimum time to collect Apple
 * 
 * Description - https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/
 * 
 * Key - This is a greedy algorithm - not the best one
 * 
 * @author Jason Zhang
 *
 */
public class _1443_MinTimeToCollectApple {
  public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
    int[] parents = new int[n];
    Arrays.fill(parents, -1);

    parents[0] = 0;

    for (int i = 0; i < edges.length; i++) {
      if (parents[edges[i][1]] == -1) {
        parents[edges[i][1]] = edges[i][0];
      } else {
        parents[edges[i][0]] = edges[i][1];
      }
    }

    int ret = 0;

    boolean[] visited = new boolean[n];
    visited[0] = true;

    for (int i = 0; i < n; i++) {
      int count = 0;

      for (int j = i; !visited[j] && hasApple.get(j); j = parents[j]) {
        count++;
        visited[j] = true;
        hasApple.set(parents[j], true);
      }
      ret += count * 2;
    }
    return ret;
  }
}
