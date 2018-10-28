package crack_code_interview;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.Assert;
import org.junit.Test;

public class Q1_4_Palindrome_Permutation {
  
  public static boolean isPalidrome(String source){
    Objects.requireNonNull(source);
    
    final int MAX_SIZE = 256;
    
    boolean[] manualSet = new boolean[MAX_SIZE];
    
    int resultOdd = 0;
    
    for(char current : source.toCharArray()) {
      if (current == ' ' ) {
        continue;
      }
      manualSet[current] = !manualSet[current];
      
      if (manualSet[current]) {
        resultOdd++;
      } else {
        resultOdd--;
      }
    }
    
    return resultOdd <=1;
  }
  
  
  @Test
  public void testIsPalidrome() {
    Object[][] data = new Object[][] {{"abcba",true},{"abccba", true},{"abcdba", false}, {"abcc b a", true}};
    List<Object[]> testData = Arrays.asList(data);
    
    for (Object[] current : testData) {
      Assert.assertEquals(current[1], isPalidrome((String)current[0]));
    }
    
  }
}
