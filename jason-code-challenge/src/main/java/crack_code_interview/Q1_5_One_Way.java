package crack_code_interview;

import java.util.Objects;

public class Q1_5_One_Way {
    public static boolean isOneWay(String first, String second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        
        
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }
        
        String longer = first.length() >= second.length() ? first : second;
        String shorter = first.length() < second.length() ? first : second;
        
        boolean findFirstDiff = false;
        
        int indexL = 0;
        int indexS = 0;
        
        while(indexL < longer.length() && indexS < shorter.length()) {
            if (longer.charAt(indexL) != shorter.charAt(indexS)) {
                if (findFirstDiff) {
                    return false;
                }
                
                findFirstDiff = true;
                
                if (longer.length() == shorter.length()) {
                    indexS++;
                }
            } else {
                indexS++;
            }
            
            indexL++;
        } 
        
        
        return true;
    }
}
