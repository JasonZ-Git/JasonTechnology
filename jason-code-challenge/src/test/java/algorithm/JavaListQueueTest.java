package algorithm;

import org.jason.algorithm.JavaListQueue;
import org.junit.Assert;
import org.junit.Test;

public class JavaListQueueTest {

  @Test
  public void testQueue() {
    JavaListQueue<Integer> queue = new JavaListQueue<Integer>();

    int size = 100;
    for (int index = 0; index < size; index++) {
      queue.enqueue(index + 1);
    }

    int current = queue.dequeue();
    Assert.assertEquals(1, current);

    current = queue.dequeue();
    Assert.assertEquals(2, current);

    current = queue.dequeue();
    Assert.assertEquals(3, current);
  }

  @Test
  public void testSize() {
    JavaListQueue<Integer> queue = new JavaListQueue<Integer>();
    Assert.assertEquals(0, queue.size());

    for (int index = 0; index < 12; index++) {
      queue.enqueue(index);
    }
    Assert.assertEquals(12, queue.size());
  }

  @Test
  public void testPerformance() {
    JavaListQueue<Integer> queue = new JavaListQueue<Integer>();

    int size = (int) Math.pow(10l, 5l);
    for (int index = 0; index < size; index++) {
      queue.enqueue(index + 1);
    }

    for (int index = 0; index < size; index++) {
      queue.dequeue();
    }
  }
}
