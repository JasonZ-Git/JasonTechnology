package crack_code_interview_test;

import org.jason.code_practice.Q2_5_Sum_List;
import org.jason.code_practice.common.LinkedListNode;
import org.junit.Assert;
import org.junit.Test;

public class Q2_5_Sum_List_Test {

    @Test
    public void testSumList() {
        LinkedListNode first = new LinkedListNode();

        LinkedListNode next1 = new LinkedListNode(7);

        first.setNext(next1);

        LinkedListNode next2 = new LinkedListNode(1);

        next1.setNext(next2);

        LinkedListNode next3 = new LinkedListNode(6);

        next2.setNext(next3);


        LinkedListNode second = new LinkedListNode();

        LinkedListNode next4 = new LinkedListNode(5);
        second.setNext(next4);

        LinkedListNode next5 = new LinkedListNode(9);
        next4.setNext(next5);

        LinkedListNode next6 = new LinkedListNode(2);
        next5.setNext(next6);

        LinkedListNode result = Q2_5_Sum_List.sumList(first, second);

        Assert.assertEquals(result.getNext().getData(), 2);
        Assert.assertEquals(result.getNext().getNext().getData(), 1);
        Assert.assertEquals(result.getNext().getNext().getNext().getData(), 9);
    }
}
