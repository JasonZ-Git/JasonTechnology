package org.jason.code_practice;

import java.util.Objects;

public class Q1_6_String_Compression {

  /**
   * Assert the original is pure upper and lower letters, Return original string if no compressed String is not shorter
   */
  public static String compress(String original) {
    Objects.requireNonNull(original);

    StringBuilder compressed = new StringBuilder();
    int count = 1;

    for (int index = 1; index < original.length(); ++index) {
      if (original.charAt(index) == original.charAt(index - 1)) {
        count++;
      } else {
        compressed.append(original.charAt(index - 1)).append(count);
        count = 1;
      }
    }
    
    compressed.append(original.charAt(original.length()-1)).append(count);
    
    return compressed.length() >= original.length()? original : compressed.toString();
  }
}
