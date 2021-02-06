public class Question122 {
    /**
     * 只要今天比昨天股价高，就可以卖
     * @param prices 每日的股价
     * @return 总收益
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        int i, num_days = prices.length;
        int former = Integer.MAX_VALUE;
        for(i=0;i<num_days;i++){
            if(prices[i]>former){
                profit += (prices[i]-former);
            }
            former = prices[i];
        }
        return profit;
    }
}
