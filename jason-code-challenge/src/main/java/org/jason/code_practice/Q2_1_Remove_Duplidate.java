package org.jason.code_practice;

import org.jason.code_practice.common.LinkedListNode;

public class Q2_1_Remove_Duplidate {
    public static void removeDuplicate(LinkedListNode node) {
        LinkedListNode head = node.getHead();
        
        LinkedListNode current = head;
        while (current.getNext() != null) {
            LinkedListNode next = current.getNext();
            while(next.getNext() != null) {
               if (current.getData() == next.getData()) {
                   next.getPrev().setNext(next.getNext());
               }
               next = next.getNext();
            }
            current = current.getNext();
        }
        
    }
}
