package algorithm;

import org.jason.algorithm.RobotLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RobotLoaderTester {

  @Test
  public void testLoader1() {
    String result = RobotLoader.roboticInsturction("PML");
    Assertions.assertEquals("0100000000", result);
  }

  @Test
  public void testLoader2() {
    String result = RobotLoader.roboticInsturction("PMLPML");
    Assertions.assertEquals("0200000000", result);
  }

  @Test
  public void testLoade3() {
    String result = RobotLoader.roboticInsturction("PMLNPML");
    Assertions.assertEquals("0200000000", result);
  }

  @Test
  public void testLoade4() {
    String result = RobotLoader.roboticInsturction("PMLLLPML");
    Assertions.assertEquals("0200000000", result);
  }

  @Test
  public void testLoade5() {
    String result = RobotLoader.roboticInsturction("PMMMMMMLPMML");
    System.out.println(result);
    Assertions.assertEquals("0010001000", result);
  }

  @Test
  public void testLoade6() {
    String result = RobotLoader.roboticInsturction("PMMMMMMMMMMMMLPMML");
    System.out.println(result);
    Assertions.assertEquals("0010000001", result);
  }
}
