package org.jason.learn.google;

/**
 * Question from Google Advanced Programming, Solution designed by Jason
 *
 * <p><strong>The Challenge</strong>
 * <p>
 * In this exercise, you're going to decompress a compressed string.
 * <p>
 * Your input is a compressed string of the format number[string] and the decompressed output form should be the string written number times.
 *
 * <p>For example:
 * <p>
 * The input
 *
 * <tt>3[abc]4[ab]c</tt>
 * <p>
 * Would be output as
 *
 * <tt>abcabcabcababababc</tt>
 *
 * <p><strong>Other rules</strong>
 * Number can have more than one digit. For example, <tt>10[a]</tt> is allowed, and just means <tt>aaaaaaaaaa</tt>
 * <p>
 * One repetition can occur inside another. For example, <tt>2[3[a]b]</tt> decompresses into aaabaaab
 * <p>
 * Characters allowed as input include digits, small English letters and brackets <tt>[ ]</tt>.
 * <p>
 * Digits are only to represent amount of repetitions.
 * <p>
 * Letters are just letters.
 * <p>
 * Brackets are only part of syntax of writing repeated substring.
 * <p>
 * Input is always valid, so no need to check its validity.
 *
 * @author Jason Zhang
 */

public class StringDecompress {

    public static String decompress(String input) {

        if (input == null || input.length() == 1) {
            return input;
        }

        int leftBracePos = input.indexOf("[");

        if (leftBracePos == -1) {
            return input;
        }

        int matchedRightBracePos = matchedRightBracePosition(input, leftBracePos + 1);

        if (matchedRightBracePos == -1) {
            throw new IllegalArgumentException("input is not valid, missing ']'");
        }

        int firstDigitPos = firstDigit(input.substring(0, leftBracePos));

        int times = Integer.parseInt(input.substring(firstDigitPos, leftBracePos));

        return input.substring(0, firstDigitPos) + repeat(decompress(input.substring(leftBracePos + 1, matchedRightBracePos)), times) + decompress(input.substring(matchedRightBracePos + 1));
    }

    private static int matchedRightBracePosition(String input, int startingPosition) {

        int count = 1;
        for (int index = startingPosition; index < input.length(); index++) {
            if (input.charAt(index) == '[') {
                count++;
            } else if (input.charAt(index) == ']') {
                count--;
            }

            if (count == 0) {
                return index;
            }
        }

        return -1;
    }


    private static int firstDigit(String input) {
        for (int index = 0; index < input.length(); index++) {
            if (input.charAt(index) >= '0' && input.charAt(index) <= '9') {
                return index;
            }
        }

        return -1;
    }

    private static String repeat(String input, int times) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < times; i++) {
            sb.append(input);
        }

        return sb.toString();
    }
}
