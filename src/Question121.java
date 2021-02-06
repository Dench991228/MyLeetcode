public class Question121 {
    /**
     * 遍历数组，记录当前股票最低价格，考虑此时卖出去是不是赚的最多
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int i, num_days = prices.length;
        int min_price = Integer.MAX_VALUE;
        int cur_max = Integer.MIN_VALUE;
        for(i=0;i<num_days;i++){
            cur_max = Math.max(cur_max,prices[i]-min_price);
            min_price = Math.min(min_price,prices[i]);
        }
        return cur_max>=0?cur_max:0;
    }
}
