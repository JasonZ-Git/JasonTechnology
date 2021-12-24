package leetcode;

/**
 * Leetcode 236 - Lowest Common Ancestor of a Binary Tree
 * 
 * Description - https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * 
 * Key - DFS - return true if left or right is true
 * 
 * @author Jason Zhang
 *
 */
public class _236_LowestCommonAncestorOfaBinaryTree {
  private TreeNode commAnce;

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    dfs(root, p.val, q.val);

    return this.commAnce;
  }

  private boolean dfs(TreeNode node, int p, int q) {
    if (node == null)
      return false;

    boolean lsearch = dfs(node.left, p, q);

    boolean rsearch = dfs(node.right, p, q);

    if ((node.val == p || node.val == q) && (lsearch ^ rsearch)) {
      commAnce = node;
    }

    if (lsearch && rsearch) {
      commAnce = node;
    }

    return lsearch || rsearch || node.val == p || node.val == q;
  }


  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
}
