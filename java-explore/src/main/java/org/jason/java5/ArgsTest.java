package org.jason.java5;

public class ArgsTest {

  public static void main(String[] args) {
    test();
  }
  
  public static void test(int ...args) {
    for(int i : args) {
      System.out.println("Called");
    }
    System.out.println("Finished");
    
  }

}
