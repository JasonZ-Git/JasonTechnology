package algorithm;

import org.jason.algorithm.JavaArrayQueue;
import org.junit.Test;

import junit.framework.Assert;

public class JavaArrayQueueTest {

  @Test
  public void testQueue(){
    JavaArrayQueue<Integer> queue= new JavaArrayQueue<Integer>();
    
    int size  = 100;
    for(int index = 0; index < size; index++) {
      queue.enqueue(index+1);
    }
    
    int current  = queue.dequeue();
    Assert.assertEquals(1, current);
    
    current  = queue.dequeue();
    Assert.assertEquals(2, current);
   
    current  = queue.dequeue();
    Assert.assertEquals(3, current);
    
  }
  
  @Test
  public void testSize(){
    JavaArrayQueue<Integer> queue= new JavaArrayQueue<Integer>();
    Assert.assertEquals(0, queue.size());
    
    for(int index = 0; index < 12; index++){
      queue.enqueue(index);
    }
    Assert.assertEquals(12, queue.size());
  }
  
  @Test
  public void testPerformance(){
    int size  = (int)Math.pow(10l, 5l);
    JavaArrayQueue<Integer> queue= new JavaArrayQueue<Integer>();
    
    for(int index = 0; index < size; index++) {
      queue.enqueue(index+1);
    }
    
    for(int index = 0; index < size; index++) {
      queue.dequeue();
    }
    
  }
}
