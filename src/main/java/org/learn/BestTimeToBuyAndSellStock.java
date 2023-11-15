package org.learn;

public class BestTimeToBuyAndSellStock {

    private static int maxProfit(int[] prices) {
        int profit = 0;
        int buyPrice = 0;
        int sellPrice = 0;

        for(int i = 0; i < prices.length; i++){
            if(i == 0) {
                buyPrice = prices[i];
                continue;
            }
            int tmpProfit = prices[i] - buyPrice;

            if(tmpProfit > profit) {
                profit = tmpProfit;
                sellPrice = prices[i];
            }

            if(buyPrice > prices[i]) {
                buyPrice = prices[i];
            }
        }

        return profit;
    }

    private static int bestSolution(int[] prices) {
        int lowest = Integer.MAX_VALUE;
        int gain = 0;
        int diff = 0;

        for(int i = 0; i < prices.length; i++){
            if(prices[i] < lowest){
                lowest = prices[i];
            }
            diff = prices[i] - lowest;
            if(gain < diff){
                gain = diff;
            }
        }
        return gain;
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};

        System.out.println(maxProfit(prices));

    }
}
