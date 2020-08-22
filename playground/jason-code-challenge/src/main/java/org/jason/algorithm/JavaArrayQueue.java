package org.jason.algorithm;

import java.util.Arrays;

/**
 * Implementation of 'Queue' structure.
 *
 * @author Jason.Zhang
 */
public class JavaArrayQueue<T> {

    private T[] elementsData;

    private static final int DEFAULT_CAPICITY = 10;

    private int size = 0;

    @SuppressWarnings("unchecked")
    public JavaArrayQueue() {
        this.elementsData = (T[]) new Object[DEFAULT_CAPICITY];
    }

    public JavaArrayQueue(int capicity) {
        if (capicity <= 0) {
            throw new IllegalArgumentException();
        }
        this.elementsData = (T[]) new Object[capicity];
    }

    /**
     * Pop the element at the top.
     *
     * @return
     */
    public T dequeue() {
        if (this.size == 0) {
            throw new RuntimeException("No element in the array");
        }

        T result = elementsData[0];

        for (int index = 0; index < size; index++) {
            elementsData[index] = elementsData[index + 1];
        }

        size--;

        return result;
    }

    /**
     * @param t
     */
    public void enqueue(T t) {
        if (this.elementsData.length == this.size) {
            this.elementsData = Arrays.copyOf(this.elementsData, this.elementsData.length * 2);
        }
        this.elementsData[size++] = t;
    }

    public int size() {
        return this.size;
    }
}
