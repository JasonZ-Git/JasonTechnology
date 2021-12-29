package com.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Leetcode - 139 - Word Break
 * 
 * Description - https://leetcode.com/problems/word-break/
 * 
 * Key -
 * 
 * @author Jason Zhang
 *
 */
public class _139_WordBreak {

  public boolean wordBreak(String s, List<String> wordDict) {
    return expand(s, wordDict, new HashSet<>(wordDict));
  }

  private boolean expand(String s, List<String> wordDict, Set<String> result) {
    if (result.contains(s))
      return true;

    Set<String> newResult = new HashSet<>();
    for (String word : wordDict) {
      for (String curr : result) {
        if (s.startsWith(curr + word)) {
          newResult.add(curr + word);
        }
      }
    }
    if (newResult.isEmpty())
      return false;

    return expand(s, wordDict, newResult);
  }
}
