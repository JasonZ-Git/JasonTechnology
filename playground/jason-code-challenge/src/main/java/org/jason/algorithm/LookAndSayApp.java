package org.jason.algorithm;

public class LookAndSayApp {
  public static String lookAndSay(String input) {
    StringBuilder sbuilder = new StringBuilder();

    char[] inputChars = (input.substring(1) + "$").toCharArray();
    int times = 1;
    char origal = input.charAt(0);
    for (char current : inputChars) {
      if (current != origal) {
        sbuilder.append(times + "" + origal);
        origal = current;
        times = 1;
      } else {
        times++;
      }
    }
    return sbuilder.toString();
  }


  public static void main(String[] args) {
    System.out.println("112111 is read as " + lookAndSay("112111") + ", 2 iterator " + lookAndSay(lookAndSay("112111")));
    System.out.println("13333522111 is read as " + lookAndSay("13333522111"));
  }

  public String lookAndSay(String input, int iterator) {
    String tempResult = input;

    for (int count = 0; count < iterator; iterator++) {
      tempResult = lookAndSay(tempResult);
    }
    return tempResult;
  }
}
