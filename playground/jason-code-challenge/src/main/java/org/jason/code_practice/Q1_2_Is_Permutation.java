package org.jason.code_practice;

import java.util.Arrays;
import java.util.Objects;

public class Q1_2_Is_Permutation {
  public static boolean isPermutationIWithOrder(String part, String whole) {
    Objects.requireNonNull(part);
    Objects.requireNonNull(whole);

    return whole.contains(part);
  }

  public static boolean isPermutationWithoutOrder(String part, String whole) {
    Objects.requireNonNull(part);
    Objects.requireNonNull(whole);

    char[] sortedPart = part.toCharArray();
    Arrays.sort(sortedPart);
    char[] sortedWhole = whole.toCharArray();
    Arrays.sort(sortedWhole);

    return sortedPart.equals(sortedWhole);
  }


  public static boolean permutation(String whole, String part) {
    if (whole.length() != part.length()) {
      return false;
    }

    int[] letters = new int[256];

    char[] s_array = whole.toCharArray();
    for (char c : s_array) { // count number of each char in s.
      letters[c]++;
    }

    for (int i = 0; i < part.length(); i++) {
      int c = (int) part.charAt(i);
      if (--letters[c] < 0) {
        return false;
      }
    }

    return true;
  }
}
