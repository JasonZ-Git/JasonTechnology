package algorithm;

import org.jason.algorithm.JavaArrayQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JavaArrayQueueTest {

  @Test
  public void testQueue() {
    JavaArrayQueue<Integer> queue = new JavaArrayQueue<Integer>();

    int size = 100;
    for (int index = 0; index < size; index++) {
      queue.enqueue(index + 1);
    }

    int current = queue.dequeue();
    Assertions.assertEquals(1, current);

    current = queue.dequeue();
    Assertions.assertEquals(2, current);

    current = queue.dequeue();
    Assertions.assertEquals(3, current);

  }

  @Test
  public void testSize() {
    JavaArrayQueue<Integer> queue = new JavaArrayQueue<Integer>();
    Assertions.assertEquals(0, queue.size());

    for (int index = 0; index < 12; index++) {
      queue.enqueue(index);
    }
    Assertions.assertEquals(12, queue.size());
  }

  @Test
  public void testPerformance() {
    int size = (int) Math.pow(10l, 5l);
    JavaArrayQueue<Integer> queue = new JavaArrayQueue<Integer>();

    for (int index = 0; index < size; index++) {
      queue.enqueue(index + 1);
    }

    for (int index = 0; index < size; index++) {
      queue.dequeue();
    }

  }
}
