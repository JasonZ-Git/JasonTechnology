package org.jason.algorithm;

import org.apache.commons.lang.StringUtils;

public class StringReverseUtil {
  public static String reverse(String input) {
    if (StringUtils.isBlank(input) || input.length() == 1) {
      return input;
    }
    
    char[] array = input.toCharArray();
    for (int index = 0; index < input.length() / 2; index++) {
      array[index] = input.charAt(input.length() -1 - index);
      array[input.length() -1 - index] = input.charAt(index);
    }
    
    return String.valueOf(array);
  }
}
