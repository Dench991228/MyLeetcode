public class Question714 {
    // 最佳买卖股票的时机，含手续费
    public int maxProfit(int[] prices, int fee) {
        if(prices.length<2)return 0;
        //记录动态规划的信息
        //第一位：当前位于第几天
        //第二位：当前手头是否持有股票
        //值：第i天的时候，在持有或不持有股票的时候，最多有多少钱
        //转移方程：
            //dp[i][0] = max(dp[i-1][1]+prices[i]-fee, dp[i-1][0]) 这回合买不买股票
            //dp[i][1] = max(dp[i-1][0]-prices[1], dp[i-1][1]) 这回合买不买股票
        //初始条件：
            //dp[0][0] = 0
            //dp[0][1] = -prices[0]
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        int i, len = prices.length;
        for(i=1;i<len;i++){
            dp[i][0] = Math.max(dp[i-1][1]+prices[i]-fee, dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][0]-prices[i], dp[i-1][1]);
        }
        return dp[i-1][0];
    }
}
