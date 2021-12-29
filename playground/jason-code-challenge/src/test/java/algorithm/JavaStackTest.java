package algorithm;

import org.jason.algorithm.JavaStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JavaStackTest {

  @Test
  public void testEmptystack() {
    JavaStack<Integer> stack = new JavaStack<Integer>();
    int size = stack.size();
    Assertions.assertEquals(0, size);
  }


  @Test
  public void testSize() {
    JavaStack<Integer> stack = new JavaStack<Integer>();
    stack.push(1);
    stack.push(2);
    stack.push(3);

    Assertions.assertEquals(3, stack.size());
    stack.pop();
    Assertions.assertEquals(2, stack.size());
  }

  @Test
  public void testPop() {
    JavaStack<Integer> stack = new JavaStack<Integer>();
    stack.push(1);
    stack.push(2);
    stack.push(3);

    int current = stack.pop();

    Assertions.assertEquals(3, current);

    current = stack.pop();

    Assertions.assertEquals(2, current);

    current = stack.pop();

    Assertions.assertEquals(1, current);
  }

  @Test
  public void testPush() {
    JavaStack<Integer> stack = new JavaStack<Integer>();
    stack.push(1);

    Assertions.assertEquals(1, stack.size());
  }

  @Test
  public void testPopEmpty() {
    JavaStack<Integer> stack = new JavaStack<Integer>();
    Assertions.assertThrows(Exception.class, () -> stack.pop());
  }

  @Test
  public void testPushExceedDefaultCapicity() {
    JavaStack<Integer> stack = new JavaStack<Integer>();
    for (int index = 0; index < 12; index++) {
      stack.push(index);
    }
    Assertions.assertEquals(12, stack.size());
  }

}
