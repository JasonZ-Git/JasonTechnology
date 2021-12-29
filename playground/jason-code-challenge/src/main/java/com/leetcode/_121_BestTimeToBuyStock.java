package com.leetcode;

/**
 * Leetcode 121 - Best time to buy Stock
 * 
 * Description - https://leetcode.com/problems/best-time-to-buy-and-sell-stock
 * 
 * Key - Time to reset min if prices[i] < min
 * 
 * @author Jason Zhang
 *
 */
public class _121_BestTimeToBuyStock {
  public int maxProfit(int prices[]) {
    int minprice = Integer.MAX_VALUE;
    int maxprofit = 0;
    for (int i = 0; i < prices.length; i++) {
      if (prices[i] < minprice)
        minprice = prices[i];
      else if (prices[i] - minprice > maxprofit)
        maxprofit = prices[i] - minprice;
    }
    return maxprofit;
  }
}
