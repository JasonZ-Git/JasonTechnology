package org.jason.test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class JavaStreamTester {
  public void testStreamReudce() {
    IntStream.rangeClosed(1, 10).forEach(System.out::println);
  }

  public void testStreamRange() {
    System.out.println(IntStream.rangeClosed(1, 10).reduce(10, Math::max));

    Stream.iterate(1, i -> i + 1).limit(100).forEach(System.out::println);
  }

  // @Test
  public void testStreamFilter() {
    List<String> allStrings = Arrays.asList("abc", "hello", "", "world");
    allStrings.stream().filter(item -> !item.isEmpty()).forEach(System.out::println);
    System.out.println("===== Test filter equals to 'hello'");
    allStrings.stream().filter(item -> item.equals("hello")).forEach(System.out::println);
  }

  // @Test
  public void testStreamGenerator() throws InterruptedException {
    Stream.generate(Person::new).limit(100).forEach(System.out::println);
  }


  // @Test
  public void testRandom() {
    Random random = new Random(1);
    random.ints(10).forEach(System.out::println);

    random.ints(10, 0, 100).forEach(System.out::println);
  }

  // @Test
  public void testIntStream() {
    System.out.println();
    System.out.println("IntStream.range test : ");
    IntStream.rangeClosed(1, 10).forEach(System.out::println);
    System.out.println("IntStream.range test : ");
    System.out.println();
  }

  @Test
  public void testStreamPeek() {
    Stream.generate(() -> new Person("Jason")).limit(10).peek(item -> item.changeName())
        .map(item -> item.getName()).sorted().forEach(System.out::println);
  }

  @Test
  public void testStreamColloctor() {
    List<Person> aList = Stream.of(1, 2, 3).map(item -> new Person(Integer.toString(item)))
        .collect(Collectors.toList());
    List<Double> bList = Stream.generate(Math::random).limit(10).collect(Collectors.toList());

    String result = Stream.generate(() -> new Person("Jason")).limit(10).peek(Person::changeName)
        .map(item -> item.getName()).collect(Collectors.joining(":"));
    System.out.println("The result of the joining is " + result);

    Stream.generate(() -> new Person("Jason")).limit(10).peek(Person::changeName)
        .map(item -> item.getName()).collect(Collectors.averagingInt(String::length));
  }

  @Test
  public void testCollection() {
    double result = IntStream.rangeClosed(1, 10).average().getAsDouble();
    System.out.println(result);
  }
  
  @Test
  public void testIterator() {
    Stream.iterate(1, i->i+1).limit(10).forEach(System.out::println);
  }

  private static class Person {
	private static int count = 9;

	private String name = "hello";

	public Person() {
	}

	public Person(String name) {
  	  this.name = name;
	}

	public void changeName() {
	  this.name = name + " === " + count--;
	}

	public String getName() {
	  return this.name;
	}

	@Override
	public String toString() {
	  return this.name + count++;
	}
  }
}

