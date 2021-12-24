package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Leetcode 1200 - Minimum Absolute Difference
 * 
 * Description - https://leetcode.com/problems/minimum-absolute-difference/
 * 
 * Key - Sort & traversal
 * 
 * @author Jason Zhang
 *
 */
public class _1200_Minimum_Absolute_Difference {
  public List<List<Integer>> minimumAbsDifference(int[] arr) {
    Arrays.sort(arr);

    int minPairDiff = Integer.MAX_VALUE;
    List<List<Integer>> answer = new ArrayList();
    for (int i = 0; i < arr.length - 1; ++i) {
      int currPairDiff = arr[i + 1] - arr[i];

      if (currPairDiff == minPairDiff) {
        answer.add(Arrays.asList(arr[i], arr[i + 1]));
      } else if (currPairDiff < minPairDiff) {
        answer.clear();
        answer.add(Arrays.asList(arr[i], arr[i + 1]));
        minPairDiff = currPairDiff;
      }
    }

    return answer;
  }
}
