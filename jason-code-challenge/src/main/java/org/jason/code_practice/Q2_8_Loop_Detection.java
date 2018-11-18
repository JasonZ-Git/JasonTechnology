package org.jason.code_practice;

import org.jason.code_practice.common.LinkedListNode;

public class Q2_8_Loop_Detection {

  public static LinkedListNode getLoopStart(LinkedListNode nodeHead) {

    LinkedListNode current = nodeHead;

    while (current.getNext() != null) {
      LinkedListNode next = current.getNext();

      do {
        if (current == next) {
          return current;
        }
        
        next = next.getNext();
      } while (next != null);


      current = next;
    }

    return null;
  }
}
