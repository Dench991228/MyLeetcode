public class Question188 {
    /**
     * 无限次交易的计算方法
     * @param prices
     * @return
     */
    private int maxProfitInf(int[] prices) {
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
    public int maxProfit(int k, int[] prices) {
        /*一共多少天*/
        int num_days = prices.length;
        /*最多交易多少次*/
        int max_trade_time = Math.max(k,prices.length+1);
        if(k>prices.length){
            return this.maxProfitInf(prices);
        }
        //用来记录动态规划的数组
        //第一位：今天是第几天
        //第二位：已经完成了多少次交易
        //第三位：手上有没有股票
        //转移方程：
            // dp[i][k][1] = max(dp[i-1][k][1],dp[i][k][0]-prices[i]) 关键在于第i天买不买股票
            // dp[i][k][0] = max(dp[i-1][k-1][1]+prices[i],dp[i-1][k][0]) 关键在于第i天的时候卖不卖股票
        //初始条件:
            // dp[0][k][0] = Integer.MIN_VALUE 没有进行任何买卖
                // dp[0][0][0] = 0
            // dp[0][k][1] = Integer.MIN_VALUE 完成k次完整交易的基础上，又买了一次股票
                // dp[0][0][1] = -prices[i]
            // dp[i][0][0] = 0 第i天，没有进行任何完整交易
            // dp[i][0][1] = 目前为止的最小值 第i天，今天第一次买股票
        int[][][] dp = new int[2][max_trade_time+1][2];//使用滚动数组
        int former = 1;
        /*开始初始化工作*/
        int i, j;
        for(i=0;i<=max_trade_time;i++){
            dp[0][i][0] = Integer.MIN_VALUE; //第0天，不论完成多少次完整交易，都是0
            dp[0][i][1] = Integer.MIN_VALUE; //第0天，不论已经完成多少次完整交易，并且手中持有股票，都是最小值（不存在），除非i=1
        }
        dp[0][0][1] = -prices[0]; //修正第0天就买入股票的问题
        dp[0][0][0] = 0;
        /*用来在滚动数组每一次滚动的时候进行初始化工作*/
        int min_price = Math.min(Integer.MAX_VALUE,prices[0]);
        /*计算剩余的部分*/
        //遍历每一天
        for(i=1;i<num_days;i++){
            former^=1;
            //遍历每一天之前先进行初始化
            min_price = Math.min(min_price,prices[i]);
            dp[former][0][0] = 0;
            dp[former][0][1] = -min_price;
            //计算剩下的部分
            for(j=1;j<=max_trade_time;j++){
                dp[former][j][0] = Math.max(dp[former^1][j-1][1]+prices[i],dp[former^1][j][0]);
                dp[former][j][1] = Math.max(dp[former][j][0]-prices[i],dp[former^1][j][1]);
            }
        }
        int max_profit = 0;
        /*统计最终答案*/
        for(i=0;i<=max_trade_time;i++){
            max_profit = Math.max(dp[former][i][0],max_profit);
        }
        return max_profit;
    }
}
