package leetcode;

/**
 * Leetcode 696 - Group By Character
 * 
 * Description - https://leetcode.com/problems/count-binary-substrings/
 * 
 * Key - Math.min(groups[i - 1], groups[i])
 * 
 * @author Jason Zhang
 *
 */
public class _696_GroupByCharacter {
  public int countBinarySubstrings(String s) {
    int[] groups = new int[s.length()];
    int t = 0;
    groups[0] = 1;
    for (int i = 1; i < s.length(); i++) {
      if (s.charAt(i - 1) != s.charAt(i)) {
        groups[++t] = 1;
      } else {
        groups[t]++;
      }
    }

    int ans = 0;
    for (int i = 1; i <= t; i++) {
      ans += Math.min(groups[i - 1], groups[i]);
    }
    return ans;
  }
}
