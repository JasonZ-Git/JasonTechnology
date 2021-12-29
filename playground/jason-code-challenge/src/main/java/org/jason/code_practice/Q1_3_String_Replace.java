package org.jason.code_practice;

import java.util.Objects;

public class Q1_3_String_Replace {
  public static String replaceSpace(String source) {
    Objects.requireNonNull(source);

    return source.replace(" ", "%20");
  }

  public static char[] replaceSpace2(char[] source, int length) {

    final char SPACE = ' ';

    int newLength = length;
    for (int i = 0; i < length; ++i) {
      if (source[i] == SPACE) {
        newLength += 2;
      }
    }

    int backIndex = newLength - 1;

    for (int i = length - 1; i >= 0; i--) {
      if (source[i] == SPACE) {
        source[backIndex] = '0';
        source[backIndex - 1] = '2';
        source[backIndex - 2] = '%';
        backIndex -= 3;
      } else {
        source[backIndex] = source[i];
        backIndex--;
      }
    }

    return source;

  }


}
