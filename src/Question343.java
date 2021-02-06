public class Question343 {
    /*整数切分*/
    /*使用动态规划解决问题*/
    /*考虑n切分出来之后最大的整数*/

    /**
     * 得到num的，以num-1~1为最大切分整数的最大乘积，并把结果放回到数组中
     * @param result result[i][j]记录的是i以j为最大整数的切分出来的最大乘积
     * @param num 当前要切分的数
     */
    private void getDp(int[][] result, int num){
        int i,j;
        /*遍历num可能的最大的切分出来的整数，注意：最大切分数可以等于本身*/
        for(i=1;i<=num;i++){
            result[num][i] = 0;
            /*遍历num-i可能的，全部最大切分数<=i的可能性*/
            for(j=0;j<=i;j++){
                result[num][i] = Math.max(result[num][i], i*result[num-i][j]);
            }
        }
    }
    public int integerBreak(int n) {
        int[][] result = new int[n+1][n+1];
        result[1][1] = 1;
        result[0][0] = 1;
        int i;
        if(n==1)return 1;
        for(i=2;i<=n;i++){
            this.getDp(result,i);
        }
        int multiply = 0;
        for(i=1;i<n;i++){
            multiply=Math.max(multiply,result[n][i]);
        }
        return multiply;
    }
}
