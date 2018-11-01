package crack_code_interview;

public class Q1_9_String_Rotation {

    public static boolean rotationEqual(String first, String second) {
        if (first == null || second == null || first.length() != second.length() || first.equals(second)) {
            return false;
        }
        
        return isSubString(first+first, second);
    }

    private static boolean isSubString(String big, String small) {
        return big.indexOf(small) != -1;
    }
}
