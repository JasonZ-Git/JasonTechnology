package leetcode;

/**
 * Leetcode 459 - Repeated String Pattern
 * 
 * Description - https://leetcode.com/problems/repeated-substring-pattern/
 * 
 * @author Jason Zhang
 *
 */
public class RepeatedSubstringPattern_459 {
  public boolean repeatedSubstringPattern(String s) {
    String newStr = s + s;

    return newStr.substring(1, newStr.length() - 2).contains(s);
  }
}
