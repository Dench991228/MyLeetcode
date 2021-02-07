public class Question63 {
    /**
     * 上一题的升级版
     * */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int height = obstacleGrid.length;
        int width = obstacleGrid[0].length;
        /*显然没戏的情况*/
        if(obstacleGrid[height-1][width-1]==1)return 0;
        /*初始化dp*/
        obstacleGrid[height-1][width-1] = 1;
        for(int i=height-2;i>=0;i--){
            if(obstacleGrid[i][width-1]!=1)obstacleGrid[i][width-1] = obstacleGrid[i+1][width-1];
            else obstacleGrid[i][width-1] = 0;
        }
        for(int j=width-2;j>=0;j--){
            if(obstacleGrid[height-1][j]!=1)obstacleGrid[height-1][j] = obstacleGrid[height-1][j+1];
            else obstacleGrid[height-1][j] = 0;
        }
        for(int i=height-2;i>=0;i--){
            for(int j=width-2;j>=0;j--){
                if(obstacleGrid[i][j]==1)obstacleGrid[i][j]=0;
                else obstacleGrid[i][j] = obstacleGrid[i+1][j] + obstacleGrid[i][j+1];
            }
        }
        return obstacleGrid[0][0];
    }
}
