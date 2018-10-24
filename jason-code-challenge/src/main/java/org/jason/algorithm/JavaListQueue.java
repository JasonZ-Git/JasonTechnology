package org.jason.algorithm;

import java.util.NoSuchElementException;

public class JavaListQueue<T> {

  private Node head;
  private Node tail;

  private int size;

  public JavaListQueue() {
    this.size = 0;
  }

  public JavaListQueue<T> enqueue(T t) {
    Node current = this.tail;
    this.tail = new Node(t);

    if (this.size == 0) {
      this.head = this.tail;
    } else {
      current.next = this.tail;
    }
    this.size++;

    return this;
  }

  public T dequeue() {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    T temp = this.head.val;
    this.head = this.head.next;

    return temp;
  }

  public int size() {
    return this.size;
  }

  private class Node {
    T val;
    Node next;

    public Node(T val) {
      this.val = val;
      this.next = null;
    }
  }

}
