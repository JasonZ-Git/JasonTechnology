package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Leetcode 1481 - Least Number of Unique Integers after K Removals
 * 
 * Description - https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/
 * 
 * Key - Put into HashMap; Get the values out and sort
 * 
 * @author Jason Zhang
 *
 */
public class LeastNumberOfUniqueIntegersAfterKRemovals_1481 {

  public static void main(String[] args) {
    int[] arr = new int[] {5, 5, 4};
    int k = 1;
    findLeastNumOfUniqueInts(arr, k);
  }

  public static int findLeastNumOfUniqueInts(int[] arr, int k) {

    Map<Integer, Integer> keyValues = new HashMap<>();
    for (int current : arr) {
      keyValues.put(current, keyValues.getOrDefault(current, 0) + 1);
    }

    List<Integer> values = new ArrayList<>(keyValues.values());

    Collections.sort(values);

    int count = keyValues.size();
    for (Integer current : values) {
      if (k - current >= 0)
        count--;
    }

    return count;
  }
}
