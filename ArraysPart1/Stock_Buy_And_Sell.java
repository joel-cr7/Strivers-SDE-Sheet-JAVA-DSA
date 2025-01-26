package ArraysPart1;


//Problem link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/


public class Stock_Buy_And_Sell {

    /** Brute Force Approach:
     * For each element calculate the max profit for every further day using nested loops **/

    // Time Complexity ~ O(n^2)
    // Space complexity ~ O(1)

    static class Solution1 {
        public int maxProfit(int[] prices) {
            int maxProfit = Integer.MIN_VALUE;
            int n = prices.length;
            for(int i=0;i<n;i++){
                for(int j=i+1;j<n;j++){
                    maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
                }
            }
            return maxProfit < 0 ? 0 : maxProfit;
        }
    }


    /** Optimal Approach:
     * If we are selling stock on ith day price, we can maximize profit by buying stock on a minimum price from
     * 0th to (i-1)th day.
     * So for every element, keep track of the minimum on the left of array and subtract to the the max profit**/

    // Time Complexity ~ O(n)
    // Space complexity ~ O(1)

    static class Solution2 {
        public int maxProfit(int[] prices) {
            int maxProfit = 0;
            int currMin = prices[0];

            for(int i=1;i<prices.length;i++){
                currMin = Math.min(currMin, prices[i]);
                maxProfit = Math.max(maxProfit, prices[i] - currMin);
            }

            return maxProfit;
        }
    }
}
