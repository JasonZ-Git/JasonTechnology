package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.util.Pair;

/**
 * Leetcode 314 - Binary Tree Search
 * 
 * Description - https://leetcode.com/problems/binary-tree-vertical-order-traversal
 * 
 * Key - Use DFS Search, use Map<Integer, ArrayList<Pair<Integer, Integer>>> to search result
 * 
 * @author Jason Zhang
 *
 */
public class _314_BinartTreeVerticalTraverse {
  Map<Integer, ArrayList<Pair<Integer, Integer>>> columnTable = new HashMap<>();
  int minColumn = 0, maxColumn = 0;

  private void DFS(TreeNode node, Integer row, Integer column) {
    if (node == null)
      return;

    if (!columnTable.containsKey(column)) {
      this.columnTable.put(column, new ArrayList<Pair<Integer, Integer>>());
    }

    this.columnTable.get(column).add(new Pair<Integer, Integer>(row, node.val));
    this.minColumn = Math.min(minColumn, column);
    this.maxColumn = Math.max(maxColumn, column);
    // preorder DFS traversal
    this.DFS(node.left, row + 1, column - 1);
    this.DFS(node.right, row + 1, column + 1);
  }

  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> output = new ArrayList<>();
    if (root == null) {
      return output;
    }

    this.DFS(root, 0, 0);

    for (int i = minColumn; i < maxColumn + 1; ++i) {

      Collections.sort(columnTable.get(i), (p1, p2) -> p1.getFirst() - p2.getFirst());

      List<Integer> sortedColumn = new ArrayList<>();
      for (Pair<Integer, Integer> p : columnTable.get(i)) {
        sortedColumn.add(p.getSecond());
      }
      output.add(sortedColumn);
    }

    return output;
  }
}


class TreeNode {
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


