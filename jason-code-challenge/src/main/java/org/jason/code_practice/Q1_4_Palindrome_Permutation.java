package org.jason.code_practice;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
  
  

  public void main(String[] args) {
    Object[][] data = new Object[][] {{"abcba",true},{"abccba", true},{"abcdba", false}, {"abcc b a", true}};
    List<Object[]> testData = Arrays.asList(data);
    
    for (Object[] current : testData) {
      if((boolean)current[1] != isPalidrome((String)current[0])) {
          System.out.println("Test Fail");
      }
    }
    
  }
}
