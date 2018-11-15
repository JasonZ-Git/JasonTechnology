package crack_code_interview.common;

import java.util.EmptyStackException;

/**
 * 
 * @author jason zhang
 *
 */
public class JasonStack<E> {

  private StackNode<E> top;

  public E pop() {
    if (top == null) {
      return null;
    }

    E item = top.data;

    this.top = top.next;

    return item;
  };

  public void push(E item) {
    if (item == null) {
      throw new EmptyStackException();
    }

    StackNode<E> newTop = new StackNode<>(item);

    newTop.next = this.top;
    this.top = newTop;
  }
  
  public E peek() {
    if (top == null) {
      return null;
    }
    
    return top.data;
  }



  private static class StackNode<E> {
    private E data;
    private StackNode<E> next;

    public StackNode(E e) {
      this.data = e;
    }
  }

}
