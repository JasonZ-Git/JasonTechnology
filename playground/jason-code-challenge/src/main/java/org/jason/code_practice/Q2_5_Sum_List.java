package org.jason.code_practice;

import org.jason.code_practice.common.LinkedListNode;

public class Q2_5_Sum_List {
  public static LinkedListNode sumList(LinkedListNode first, LinkedListNode second) {

    LinkedListNode result = new LinkedListNode();

    LinkedListNode headResult = result;

    int factor = 1;

    int tempResult = 0;

    while (first.getNext() != null || second.getNext() != null) {
      LinkedListNode oneNext = first.getNext();
      LinkedListNode twoNext = second.getNext();

      if (oneNext != null) {
        tempResult = tempResult + oneNext.getData() * factor;
        first = first.getNext();
      }

      if (twoNext != null) {
        tempResult = tempResult + twoNext.getData() * factor;
        second = second.getNext();
      }

      int oneDigit = tempResult / factor - tempResult / (factor * 10) * 10;

      LinkedListNode next = new LinkedListNode(oneDigit);

      result.setNext(next);

      result = next;

      factor *= 10;
    }

    return headResult;
  }

}
