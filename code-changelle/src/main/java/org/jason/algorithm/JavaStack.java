package org.jason.algorithm;

import java.util.Arrays;

/**
 * Implementation of 'Stack' structure.
 * This is just a simple implementation{@link Collection} interface, the strict version should implement Collection interface.
 * 
 * @author Jason.Zhang
 *
 */
public class JavaStack<T> {

  private T[] elementsData;

  private static final int DEFAULT_CAPICITY = 10;

  private int size = 0;

  @SuppressWarnings("unchecked")
  public JavaStack() {
    this.elementsData = (T[])new Object[DEFAULT_CAPICITY];
  }

  /**
   * Pop the element at the top 
   * @return
   */
  public T pop() {
    if (this.size == 0) {
      throw new RuntimeException("No element in the array");
    }

    return elementsData[--size];
  }

  public void push(T t) {
    if (this.elementsData.length == this.size) {
      this.elementsData = Arrays.copyOf(this.elementsData, this.elementsData.length * 2);
    }
    this.elementsData[size++] = t;
  }

  public int size() {
    return this.size;
  }
}
