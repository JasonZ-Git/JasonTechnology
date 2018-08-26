package org.jason.test;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class NumberStringTest {
  
  private String toTest;
  private Boolean flag;
  
  public NumberStringTest(String toTest, Boolean flag){
    this.toTest = toTest;
    this.flag = flag;
  }
  
  @Parameterized.Parameters
  public static Collection primeNumbers(){
    return Arrays.asList(new Object[][]{
      {"1234", true},{"1234a", false}
    });
  }
  
  @Test
  public void isNumber(){
    boolean result = Pattern.compile("\\d+").matcher(this.toTest).matches();
    System.out.println("To test String " + this.toTest + " " + result);
    Assert.assertEquals(result, this.flag);
  }
}
