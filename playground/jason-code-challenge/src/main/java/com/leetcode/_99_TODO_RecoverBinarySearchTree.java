package com.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Leetcode 99 - Recover Binary Search Tree
 * 
 * Description - https://leetcode.com/problems/recover-binary-search-tree/
 * 
 * Key -
 * 
 * 
 * @author Jason Zhang
 *
 */
public class _99_TODO_RecoverBinarySearchTree {
  private void swap(TreeNode a, TreeNode b) {
    int tmp = a.val;
    a.val = b.val;
    b.val = tmp;
  }

  public void recoverTree(TreeNode root) {
    Deque<TreeNode> stack = new ArrayDeque();
    TreeNode x = null, y = null, pred = null;

    while (!stack.isEmpty() || root != null) {
      while (root != null) {
        stack.add(root);
        root = root.left;
      }
      root = stack.removeLast();
      if (pred != null && root.val < pred.val) {
        y = root;
        if (x == null)
          x = pred;
        else
          break;
      }
      pred = root;
      root = root.right;
    }

    swap(x, y);
  }


  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
}
