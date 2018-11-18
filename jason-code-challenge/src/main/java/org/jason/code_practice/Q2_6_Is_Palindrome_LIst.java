package org.jason.code_practice;

import org.jason.code_practice.common.LinkedListNode;

public class Q2_6_Is_Palindrome_LIst {
    
    //** Suppose LinkedListNode is a single direction list.
    public static boolean isPalindrome(LinkedListNode head) {
        LinkedListNode reversed = reverseAndClone(head);
        return isEqual(head, reversed);
    }
        
    public static LinkedListNode reverseAndClone(LinkedListNode node) {
        LinkedListNode head = null;
        while (node != null) {
            LinkedListNode n = new LinkedListNode(node.getData()); // Clone
            n.setNext(head);
            head = n;
            node = node.getNext();
        }
        return head;
    }   
        
    public static boolean isEqual(LinkedListNode one, LinkedListNode two) {
        while (one != null && two != null) {
            if (one.getData() != two.getData()) {
                return false;
            }
            one = one.getNext();
            two = two.getNext();
        }
        return one == null && two == null;
    }
}
