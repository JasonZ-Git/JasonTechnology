package leetcode;

import java.util.Arrays;

/**
 * Leetcode 621 - Task Schedule - Least Interval
 * 
 * Description - 
 * 
 * Key - The most interval is caused by the most frequent idles.
 * 
 * @author Jason Zhang
 *
 */
public class TaskScheduler_621 {
  public int leastInterval(char[] tasks, int n) {
    // frequencies of the tasks
    int[] frequencies = new int[26];
    for (int t : tasks) {
      frequencies[t - 'A']++;
    }

    Arrays.sort(frequencies);

    // max frequency
    int f_max = frequencies[25];
    int idle_time = (f_max - 1) * n;

    for (int i = frequencies.length - 2; i >= 0 && idle_time > 0; --i) {
      idle_time -= Math.min(f_max - 1, frequencies[i]);
    }
    idle_time = Math.max(0, idle_time);

    return idle_time + tasks.length;
  }
}
