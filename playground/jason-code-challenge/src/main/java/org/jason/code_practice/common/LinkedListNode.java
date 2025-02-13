package org.jason.code_practice.common;

/**
 * This class is originally copied from somewhere else, and only used for crack code related algorithm.
 * <p>
 * Actually, this class is bad from design point of view, because it contains 2 roles: * Servers as an container * Serves as a single element. thus breaches 'Single Responsibility' rule.
 * <p>
 * In actual design, it should be designed as 2 classes, one servers as an container (has head and last node), and another inner class as the actual element.
 *
 * @author jason zhang
 * @see java.util.LinkedList
 */
public class LinkedListNode {
  private LinkedListNode next;
  private LinkedListNode prev;
  private LinkedListNode tail;
  private LinkedListNode head;
  private int data;

  public LinkedListNode(int d, LinkedListNode n, LinkedListNode p) {
    data = d;
    head = n;
    setNext(n);
    setPrevious(p);
  }


  public LinkedListNode(int d) {
    data = d;
  }

  public LinkedListNode() {}

  public String printForward() {
    if (next != null) {
      return data + "->" + next.printForward();
    } else {
      return ((Integer) data).toString();
    }
  }

  public LinkedListNode clone() {
    LinkedListNode next2 = null;
    if (next != null) {
      next2 = next.clone();
    }
    LinkedListNode head2 = new LinkedListNode(data, next2, null);
    return head2;
  }

  public int getLength() {
    return next == null ? 0 : next.getLength() + 1;
  }

  public LinkedListNode getPrev() {
    return prev;
  }


  public void setPrev(LinkedListNode prev) {
    this.prev = prev;
  }


  public LinkedListNode getTail() {
    return tail;
  }


  public LinkedListNode getHead() {
    return head;
  }

  public int getData() {
    return data;
  }

  public void setData(int data) {
    this.data = data;
  }


  public LinkedListNode getNext() {
    return next;
  }

  public void setNext(LinkedListNode n) {
    if (this.head == null) {
      this.head = this;
      this.head.next = n;
      n.prev = this;

      return;
    }

    this.next = n;
    n.next = this.next.next;
    if (this == this.tail) {
      this.tail = n;
      n.setPrevious(this);
      return;
    } else {

    }
  }

  public void setPrevious(LinkedListNode p) {
    prev = p;
    if (p != null && p.next != this) {
      p.setNext(this);
    }
  }
}
