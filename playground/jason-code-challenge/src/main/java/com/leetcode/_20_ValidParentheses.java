package com.leetcode;

import java.util.HashMap;
import java.util.Stack;

/**
 * Leetcode 20 - Valid Parentheses
 * 
 * Description - https://leetcode.com/problems/valid-parentheses
 * 
 * Key - Stack
 * 
 * @author Jason Zhang
 *
 */
public class _20_ValidParentheses {


  private HashMap<Character, Character> mappings;

  public _20_ValidParentheses() {
    this.mappings = new HashMap<Character, Character>();
    this.mappings.put(')', '(');
    this.mappings.put('}', '{');
    this.mappings.put(']', '[');
  }

  public boolean isValid(String s) {

    Stack<Character> stack = new Stack<Character>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (this.mappings.containsKey(c)) {

        char topElement = stack.empty() ? '#' : stack.pop();

        if (topElement != this.mappings.get(c)) {
          return false;
        }
      } else {
        stack.push(c);
      }
    }

    return stack.isEmpty();
  }

}
