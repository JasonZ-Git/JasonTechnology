package crack_code_interview;

import crack_code_interview.common.LinkedListNode;

public class Q2_2_Print_Kth_To_Last {
    public static int printKthToLast(LinkedListNode node, int k) {
        
       if (node.getNext() == null) {
           return 0;
       }
       
       int index = printKthToLast(node.getNext(), k) + 1;
       
       if (index == k) {
           System.out.println(k + " th node is " + node.getData());
       }
        
       return k;
    }
}
