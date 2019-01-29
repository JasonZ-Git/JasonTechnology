package org.jason.learn.google;

public class StringDecompress {

    public static String decompress(String input) {

        if (input == null || input.length() == 1) {
            return input;
        }

        int leftBracePos = input.indexOf("[");

        if (leftBracePos == -1) {
            return input;
        }

        int matchedRightBracePos = matchedRightBracePosition(input, leftBracePos+1);

        if (matchedRightBracePos == -1) {
            throw new IllegalArgumentException("input is not valid, missing ']'");
        }

        int firstDigitPos = firstDigit(input.substring(0, leftBracePos));

        int times = Integer.parseInt(input.substring(firstDigitPos, leftBracePos));

        return input.substring(0, firstDigitPos) + repeat(decompress(input.substring(leftBracePos + 1, matchedRightBracePos)), times) + decompress(input.substring(matchedRightBracePos+1));
    }

    private static int matchedRightBracePosition(String input, int startingPosition) {
        
        int count = 1;
        for (int index = startingPosition; index < input.length(); index ++) {
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
