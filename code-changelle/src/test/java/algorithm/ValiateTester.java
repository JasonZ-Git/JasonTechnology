package algorithm;

import org.jason.algorithm.DataValidation;
import org.junit.Test;

import junit.framework.Assert;

public class ValiateTester {
  @Test
  public void test(){
    String current = "|name|address|~n|jason|jason@hello.org|jason@hello.com|";
    String result = DataValidation.validate(current);
    Assert.assertEquals("1:2:0:address_1", result);
  }
  
  @Test
  public void test1(){
    String current = "|name|address|~n|jason|jason@hello.org|jason@hello.com|~n|jason|jason@hello.org|jason@hello.com|";
    String result = DataValidation.validate(current);
    Assert.assertEquals("2:2:0:address_1", result);
  }
  
  @Test
  public void test2(){
    String current = "|name|address|~n|jason|jason@hello.org|jason@hello.com|jason@hello.com|~n|jason||jason@hello.org|jason@hello.com|";
    String result = DataValidation.validate(current);
    Assert.assertEquals("2:2:0:address_1", result);
  }
}
