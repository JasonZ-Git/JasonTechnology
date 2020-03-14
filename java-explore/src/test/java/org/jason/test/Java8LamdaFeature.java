package org.jason.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.jason.java8.functioninterface.JasonPredicate;
import org.junit.jupiter.api.Test;

public class Java8LamdaFeature {

  @Test
  public void lambdaFeature() {
    List<String> aList = Arrays.asList("hello", "world", "I ", "am", "Jason");
    System.out.println(aList);
    Collections.sort(aList, (a, b) -> a.compareToIgnoreCase(b));
    System.out.println(aList);
  }

  @Test
  public void functionInterfaceVersion1() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    Predicate<Integer> predicate = new Predicate<Integer>() {

      @Override
      public boolean test(Integer t) {
        return t % 2 == 0;
      }
    };

    System.out.println("Without using functional interface, version 1");
    eval(list, predicate);
  }

  @Test
  public void functionInterfaceVersion2() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    System.out.println("Using functional interface, version 2");
    eval(list, n -> n % 2 == 0);
  }

  @Test
  public void functionInterfaceVersion3() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    System.out.println("Using self created functional interface, version 3");
    evalJason(list, n -> n % 2 == 0);
  }

  @Test
  public void functionInterfaceVersion4() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    System.out.println("Using self created functional interface, version 4");
    JasonPredicate<Integer> jasonPredicate = n -> n % 2 == 0;
    evalJason(list, jasonPredicate);
  }

  public void eval(List<Integer> list, Predicate<Integer> predicate) {
    for (Integer current : list) {
      if (predicate.test(current)) {
        System.out.println(current);
      }
    }
  }

  public void evalJason(List<Integer> list, JasonPredicate<Integer> jasonPredicate) {
    for (Integer current : list) {
      if (jasonPredicate.check(current)) {
        System.out.println(current);
      }
    }
  }

}
