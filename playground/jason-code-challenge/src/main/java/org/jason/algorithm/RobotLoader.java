package org.jason.algorithm;

import java.util.HashMap;
import java.util.Map;

public class RobotLoader {
  public static void main(String[] args) {
    System.out.println("input is PMLLMP, result is " + roboticInsturction("PMLLMP"));
    System.out.println(roboticInsturction("PMLNPLM"));
  }

  public static String roboticInsturction(String input) {
    int[] loadResult = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    int currentPos = 0;
    boolean holdBlock = false;
    for (char current : input.toCharArray()) {

      switch (current) {
        case 'P':
          holdBlock = true;
          currentPos = 0;
          break;

        case 'M':
          currentPos = (currentPos == 9 ? currentPos : currentPos + 1);
          break;

        case 'L':
          if (holdBlock) {
            loadResult[currentPos] = (loadResult[currentPos] == 15 ? loadResult[currentPos] : loadResult[currentPos] + 1);
            holdBlock = false;
          }
          break;

        default:
          break;
      }
    }

    Map<Integer, Character> mapping = new HashMap<Integer, Character>();
    mapping.put(10, 'A');
    mapping.put(11, 'B');
    mapping.put(12, 'C');
    mapping.put(13, 'D');
    mapping.put(14, 'E');
    mapping.put(15, 'F');

    String result = "";
    for (int current : loadResult) {
      if (current >= 10) {
        result += mapping.get(current);
      } else {
        result += current;
      }
    }


    return result;
  }
}
