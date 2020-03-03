package org.jason.code_challenge;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class NumberChallenge {
  
  public String reverserStr(String source){
    if(source == null || source.length() <=1){
      return source;
    }
    
    return reverserStr(source.substring(1)) + source.charAt(0);
  }
  
  
  @Test
  public void testReverserStr(){
    String hello = "Hello";
    
    String result = reverserStr(hello);
    
    Assert.assertEquals("", result, "olleH");
    
    assert(2>1);
  }
  
  @Test
  public void testComparator(){
    List<Integer> here = Arrays.asList(1, 2);
    
    Collections.sort(here, (a, b) -> a-b);
  }
  
}
