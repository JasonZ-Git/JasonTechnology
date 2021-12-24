package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Leetcode 49 - Group Anagrams
 * 
 * Description - https://leetcode.com/problems/group-anagrams/
 * 
 * Key - Sort it
 * 
 * @author Jason Zhang
 *
 */
public class GroupAnagrams_49 {
  public List<List<String>> groupAnagrams(String[] strs) {
    if (strs.length == 0)
      return new ArrayList<List<String>>();
    Map<String, List<String>> ans = new HashMap<>();
    for (String s : strs) {
      char[] ca = s.toCharArray();
      Arrays.sort(ca);
      String key = String.valueOf(ca);
      if (!ans.containsKey(key))
        ans.put(key, new ArrayList());
      ans.get(key).add(s);
    }
    return new ArrayList<List<String>>(ans.values());
  }
}
