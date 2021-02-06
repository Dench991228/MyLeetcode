public class Question123 {

    /**
     * 先获得当前位置到最后的部分买卖一次股票最多多少钱，再计算从开头到当前位置卖卖一次股票最多挣多少钱，最后两个合成
     * @param prices 股票每天的价格
     * @return 买卖不超过两次股票的收益
     */
    public int maxProfit(int[] prices) {
        /*第一次买卖挣到的钱*/
        int[] firstProfit = new int[prices.length];
        int i, len = prices.length;
        int min_price = Integer.MAX_VALUE;
        int cur_profit_earlier = Integer.MIN_VALUE;//到目前为止，第一次买卖的收益
        for(i=0;i<len;i++){
            cur_profit_earlier = Math.max(cur_profit_earlier,prices[i]-min_price)>0?Math.max(cur_profit_earlier,prices[i]-min_price):0;
            firstProfit[i] = cur_profit_earlier;
            min_price = Math.min(min_price,prices[i]);
        }
        /*第二次买卖挣到的钱*/
        int[] secondProfit = new int[prices.length];
        int max_price = 0;
        int cur_profit_latter = Integer.MIN_VALUE;
        for(i=len-1;i>=0;i--){
            cur_profit_latter = Math.max(cur_profit_latter,max_price-prices[i])>0?Math.max(cur_profit_latter,max_price-prices[i]):0;
            secondProfit[i] = cur_profit_latter;
            max_price = Math.max(max_price,prices[i]);
        }
        int max_profit = Integer.MIN_VALUE;
        for(i=0;i<len;i++){
            max_profit = Math.max(firstProfit[i]+secondProfit[i],max_profit);
        }
        return max_profit;
    }
}
