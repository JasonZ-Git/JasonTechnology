package com.leetcode;

/**
 * Given a column title as appears in an Excel sheet, return its corresponding column number.
 * 
 * @author Jason Zhang
 */
public class SheetTitleNumber {
  
  public static int titleToNumber(String s) {
    int result = 0;
    for (int i = 0; i < s.length(); i++) {
      result *= 26;
      result += s.charAt(i) - 'A' + 1;
    }
    return result;
  }
}
