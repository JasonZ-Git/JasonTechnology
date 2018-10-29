package algorithm;

import org.jason.algorithm.RobotLoader;
import org.junit.Assert;
import org.junit.Test;

public class RobotLoaderTester {
  
  @Test
  public void testLoader1(){
    String result = RobotLoader.roboticInsturction("PML");
    Assert.assertEquals("0100000000", result);
  }
  
  @Test
  public void testLoader2(){
    String result = RobotLoader.roboticInsturction("PMLPML");
    Assert.assertEquals("0200000000", result);
  }
  
  @Test
  public void testLoade3(){
    String result = RobotLoader.roboticInsturction("PMLNPML");
    Assert.assertEquals("0200000000", result);
  }
  
  @Test
  public void testLoade4(){
    String result = RobotLoader.roboticInsturction("PMLLLPML");
    Assert.assertEquals("0200000000", result);
  }
  
  @Test
  public void testLoade5(){
    String result = RobotLoader.roboticInsturction("PMMMMMMLPMML");
    System.out.println(result);
    Assert.assertEquals("0010001000", result);
  }
  
  @Test
  public void testLoade6(){
    String result = RobotLoader.roboticInsturction("PMMMMMMMMMMMMLPMML");
    System.out.println(result);
    Assert.assertEquals("0010000001", result);
  }
}
