public class Question309 {
    // 股票交易，含一天的冷冻期
    public int maxProfit(int[] prices) {
        //记录动态规划的信息
            //第一位：现在是第几天
            //第二位：现在手里有没有股票
            //值：当前最多赚多少
        //转移方程：
            // dp[i][0] = max(dp[i-1][0],dp[i-1][1]+prices[i]) 这一回合有没有卖股票
            // dp[i][1] = max(dp[i-2][0]-prices[i], dp[i-1][1]) 要不要在这一回合买股票
        //初始条件：
            // dp[0][0] = 0 完全没有交易记录
            // dp[0][1] = -prices[0] 第一天就买股票
            // dp[1][0] = 转移方程
            // dp[1][1] = min(-prices[1],-prices[0])
        int[][] dp = new int[prices.length][2];
        if(prices.length<2)return 0;
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(dp[0][0],dp[0][1]+prices[1]);
        dp[1][1] = Math.max(-prices[1],-prices[0]);
        int i, len = prices.length;
        for(i=2;i<len;i++){
            dp[i][1] = Math.max(dp[i-2][0]-prices[i], dp[i-1][1]);
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
        }
        return dp[i-1][0];
    }
}
