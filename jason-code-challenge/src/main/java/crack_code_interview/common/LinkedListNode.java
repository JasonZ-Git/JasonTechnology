package crack_code_interview.common;

/**
 * This class is originally copied from somewhere else, and only used for crack code related algorithm.
 * 
 * Actually, this class is bad from design point of view, because it contains 2 roles: 
 *  * Servers as an container
 *  * Serves as a single element.
 * thus breaches 'Single Responsibility' rule.
 * 
 * In actual design, it should be designed as 2 classes, one servers as an container (has head and last node), and another inner class as the actual element.
 * 
 * @see java.util.LinkedList 
 * 
 * @author jason zhang
 *
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
        if (head == null) {
           head = this;
           head.next = n;
        }
        
        next = n;
        if (this == tail) {
            tail = n;
        }
        
        if (n != null && n.prev != this) {
            n.setPrevious(this);
        }
    }
    
    public void setPrevious(LinkedListNode p) {
        prev = p;
        if (p != null && p.next != this) {
            p.setNext(this);
        }
    }
}
