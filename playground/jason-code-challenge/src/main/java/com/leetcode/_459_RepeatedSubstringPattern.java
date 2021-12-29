package com.leetcode;

/**
 * Leetcode 459 - Repeated String Pattern
 * 
 * Description - https://leetcode.com/problems/repeated-substring-pattern/
 * 
 * Key - s => s + s contains str[1...n-1]
 * 
 * @author Jason Zhang
 *
 */
public class _459_RepeatedSubstringPattern {
  public boolean repeatedSubstringPattern(String s) {
    String newStr = s + s;

    return newStr.substring(1, newStr.length() - 2).contains(s);
  }
}
