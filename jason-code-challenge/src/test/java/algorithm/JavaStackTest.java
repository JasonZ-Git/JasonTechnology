package algorithm;

import org.jason.algorithm.JavaStack;
import org.junit.Test;

import junit.framework.Assert;

public class JavaStackTest {


  @Test
  public void testEmptystack() {
    JavaStack<Integer> stack = new JavaStack<Integer>();
    int size = stack.size();
    Assert.assertEquals(0, size);
  }


  @Test
  public void testSize() {
    JavaStack<Integer> stack = new JavaStack<Integer>();
    stack.push(1);
    stack.push(2);
    stack.push(3);

    Assert.assertEquals(3, stack.size());
    stack.pop();
    Assert.assertEquals(2, stack.size());
  }

  @Test
  public void testPop() {
    JavaStack<Integer> stack = new JavaStack<Integer>();
    stack.push(1);
    stack.push(2);
    stack.push(3);

    int current = stack.pop();

    Assert.assertEquals(3, current);
    
    current = stack.pop();

    Assert.assertEquals(2, current);
    
    current = stack.pop();

    Assert.assertEquals(1, current);
  }

  @Test
  public void testPush() {
    JavaStack<Integer> stack = new JavaStack<Integer>();
    stack.push(1);

    Assert.assertEquals(1, stack.size());
  }

  @Test(expected = Exception.class)
  public void testPopEmpty() {
    JavaStack<Integer> stack = new JavaStack<Integer>();
    stack.pop();
  }

  @Test
  public void testPushExceedDefaultCapicity() {
    JavaStack<Integer> stack = new JavaStack<Integer>();
    for (int index = 0; index < 12; index++) {
      stack.push(index);
    }
    Assert.assertEquals(12, stack.size());
  }

}
