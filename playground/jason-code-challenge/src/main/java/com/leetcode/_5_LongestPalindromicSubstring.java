package com.leetcode;

/**
 * Leetcode 5 - Longest Palindromic Substring
 * 
 * Description - https://leetcode.com/problems/longest-palindromic-substring/
 * 
 * Key - Recursive
 * 
 * @author Jason Zhang
 *
 */
public class _5_LongestPalindromicSubstring {

  public String longestPalindrome(String s) {
    String ans = "";
    for (int i = 0; i < s.length() - 1; i++) {
      for (int j = i + 1; j < s.length(); j++) {
        if (isPalindro(s, i, j) && j - i > ans.length()) {
          ans = s.substring(i, j + 1);
        }
      }
    }

    return ans;
  }

  private boolean isPalindro(String s, int i, int j) {
    if (i == j)
      return true;
    if (j - i == 1)
      return s.charAt(i) == s.charAt(j);

    return s.charAt(i) == s.charAt(j) && isPalindro(s.substring(i + 1, j - 1), i + 1, j - 1);
  }
}
