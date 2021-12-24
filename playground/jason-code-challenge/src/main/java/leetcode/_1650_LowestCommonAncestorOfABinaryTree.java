package leetcode;

import java.util.HashSet;
import java.util.Set;

public class _1650_LowestCommonAncestorOfABinaryTree {
  public ANode lowestCommonAncestor(ANode p, ANode q) {
    Set<ANode> pParent =  new HashSet<>();
    
    ANode temp = p;
    while(temp != null) {
      pParent.add(temp);
      temp = temp.parent;
    }
    
    temp = q;
    while(temp != null) {
      if (pParent.contains(temp)) return temp;
      
      temp = temp.parent;
    }
    
    return null;
    
  }
}


class ANode {
  public int val;
  public ANode left;
  public ANode right;
  public ANode parent;
};
