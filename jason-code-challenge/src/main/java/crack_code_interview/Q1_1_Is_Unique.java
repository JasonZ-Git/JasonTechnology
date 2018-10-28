package crack_code_interview;

import java.util.Objects;

public class Q1_1_Is_Unique {
  
  private static final int COMPLETE_CHAR_LENGTH = Character.MAX_CODE_POINT + 1;
  private static final int ASCII_PRINTABLE_CHAR_LENGTH = 1<<7;
  
  public static boolean isUnique(String source) {
    return isUniqueString(source, COMPLETE_CHAR_LENGTH);}
  
  public static boolean isUniqueASCII(String source) {
    return isUniqueString(source, ASCII_PRINTABLE_CHAR_LENGTH);
  }
  
  public static boolean isUniqueChars(String source) {
    Objects.requireNonNull(source);
    
    return source.length() == source.chars().distinct().count();
  }
  
  private static boolean isUniqueString(String source, final int max_length) {
    Objects.requireNonNull(source);
    
    final int MAX_LENGTH = Character.MAX_CODE_POINT +1;
    if (source.length() > max_length) {
      return false;
    }
    
    boolean[] bucket = new boolean[MAX_LENGTH];
    
    for (int index =0; index < source.length(); ++index) {
      int current = source.charAt(index);
      if (bucket[current]) {
        return false;
      }
      
      bucket[current] = true;
    }
    
    return true;
  }
  
  public static boolean isUniqueCharacterIgnoreCase(String source) {
    
    if (source == null) {
      throw new IllegalArgumentException();
    }
    
    source = source.toLowerCase();
    
    int checker = 0;
    
    for (int i = 0; i < 26; i++) {
      int val = source.charAt(i) - 'a' ;
      if ((checker & (1 << val))>0){
        return false;
      }
      
      checker = checker | (1<<val);
    }
    
    return true;
  }
}
