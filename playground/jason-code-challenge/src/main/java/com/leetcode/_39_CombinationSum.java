package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Leetcode 39 - Combination Sum
 * 
 * Description - https://leetcode.com/problems/combination-sum/
 * 
 * Key - Change it to a Route between 2 nodes - Each strech should only pick n>= previous pick
 * 
 * @author Jason Zhang
 *
 */
public class _39_CombinationSum {

  protected void backtrack(int remain, LinkedList<Integer> comb, int start, int[] candidates, List<List<Integer>> results) {

    if (remain == 0) {
      // make a deep copy of the current combination
      results.add(new ArrayList<Integer>(comb));
      return;
    } else if (remain < 0) {
      // exceed the scope, stop exploration.
      return;
    }

    for (int i = start; i < candidates.length; ++i) {
      // add the number into the combination
      comb.add(candidates[i]);
      this.backtrack(remain - candidates[i], comb, i, candidates, results);
      // backtrack, remove the number from the combination
      comb.removeLast();
    }
  }

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> results = new ArrayList<List<Integer>>();
    LinkedList<Integer> comb = new LinkedList<Integer>();

    this.backtrack(target, comb, 0, candidates, results);
    return results;
  }

}
