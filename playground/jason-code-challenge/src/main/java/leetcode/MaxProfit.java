package leetcode;

/**
 * 
  You are given an array prices where prices[i] is the price of a given stock on the ith day.

  You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

  Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 
 * @author Jason Zhang
 *
 */

public class MaxProfit {
  
  public int maxProfit(int[] prices) {
    int res = 0;
    if (prices == null || prices.length <= 1)
      return res;
    int min = prices[0];
    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > min) {
        res = Math.max(res, prices[i] - min);
      } else {
        // This is the key
        min = prices[i];
      }
    }

    return res;
  }
}
