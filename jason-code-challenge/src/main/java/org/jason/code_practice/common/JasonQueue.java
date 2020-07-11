package org.jason.code_practice.common;

/**
 * @author jason zhang
 */
public class JasonQueue<E> {

    private QueueNode<E> head;
    private QueueNode<E> tail;
    private int length;

    public void in(E e) {
        if (e == null) {
            throw new IllegalArgumentException();
        }

        QueueNode<E> newTail = new QueueNode<>(e);

        if (tail == null) {
            tail = newTail;
            head = newTail;
            length = 1;

            return;
        }

        newTail.next = this.tail;
        this.tail.previous = newTail;

        this.tail = newTail;
        length++;
    }

    public E out() {

        if (head == null) {
            return null;
        }

        QueueNode<E> newHead = head.previous;
        this.head = newHead;

        length--;

        return head.data;
    }

    private static class QueueNode<E> {
        private QueueNode<E> previous;
        private QueueNode<E> next;
        private E data;

        public QueueNode(E item) {
            this.data = item;
        }
    }
}
