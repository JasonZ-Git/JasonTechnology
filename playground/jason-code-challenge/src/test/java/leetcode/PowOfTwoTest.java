package leetcode;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.util.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class PowOfTwoTest {
  
  private List<Pair<Integer, Boolean>> testData = new ArrayList<>();
  
  @Before
  public void testSetup() {
    
    testData.add(Pair.create(-1, false));
    testData.add(Pair.create(1, true));
    testData.add(Pair.create(2, true));
    testData.add(Pair.create(1024, true));
    testData.add(Pair.create(1000, false));
    
    testData.add(Pair.create(1111, false));
    testData.add(Pair.create(10000, false));
  }
  
  @Test 
  public void testPowOfTwo() {
    
    for (Pair<Integer, Boolean> current : testData) {
      PowOfTwo.isPowerOfTwo_1(current.getFirst());
      Assert.assertEquals(PowOfTwo.isPowerOfTwo_1(current.getFirst()), current.getSecond());
      
    }
  }
  
  @Test 
  public void testPowOfTwo_2() {
    
    for (Pair<Integer, Boolean> current : testData) {
      PowOfTwo.isPowerOfTwo_1(current.getFirst());
      Assert.assertEquals(PowOfTwo.isPowerOfTwo_2(current.getFirst()), current.getSecond());
      
    }
  }
}
