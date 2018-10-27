package crack_code_interview;

import java.util.Objects;

public class Question2_Is_Permutation {
  public static boolean isPermutation(String part, String whole) {
    Objects.requireNonNull(part);
    Objects.requireNonNull(whole);
    
    return whole.contains(part);
  }
  
  public static boolean isPermutation2(String part, String whole) {
    Objects.requireNonNull(part);
    Objects.requireNonNull(whole);

    return whole.contains(part);
  }
}
