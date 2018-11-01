package crack_code_interview;

import crack_code_interview.common.LinkedListNode;

public class Q2_4_Partitation_List {
    public static void partationList(LinkedListNode node, int partion) {
        LinkedListNode head = node.getHead();
        LinkedListNode tail = node.getTail();
        
        LinkedListNode current = head;
        
        while (current.getNext() != null) {
            LinkedListNode tempNext = current.getNext();
            if (current.getData() < partion) {
                current.getPrev().setNext(current.getNext());
                current.setNext(head);
            } else if (current.getData() > partion) {
                current.getPrev().setNext(current.getNext());
                current.setNext(null);
                tail.setNext(current);
            }
            
            current = tempNext;
        }
        
    }
}
