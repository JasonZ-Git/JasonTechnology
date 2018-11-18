package crack_code_interview_test;

import org.jason.code_practice.Q2_1_Remove_Duplidate;
import org.jason.code_practice.common.LinkedListNode;
import org.junit.Assert;
import org.junit.Test;

public class Q2_1_Remove_Duplicate_Test {

    @Test
    public void testRemoveDuplate() {
        LinkedListNode node = new LinkedListNode(1);

        LinkedListNode next1 = new LinkedListNode(2);

        node.setNext(next1);

        LinkedListNode next2 = new LinkedListNode(3);

        next1.setNext(next2);

        LinkedListNode next3 = new LinkedListNode(2);

        next2.setNext(next3);

        LinkedListNode next4 = new LinkedListNode(4);

        next3.setNext(next4);
        
        
        Q2_1_Remove_Duplidate.removeDuplicate(node);
        
        Assert.assertEquals(node.getNext().getNext().getNext().getData(), 4);
        

    }
}
