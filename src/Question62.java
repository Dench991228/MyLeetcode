public class Question62 {
    /**
     * 标准的左上角到右下角的走迷宫，问一共有多少路径
     * @param m 迷宫高度
     * @param n 迷宫的长度
     * */
    public int uniquePaths(int m, int n) {
        if(m==1||n==1)return 1;
        int[][] differentPaths = new int[m][n];
        /*初始化dp*/
        for(int i=0;i<m;i++){
            differentPaths[i][n-1] = 1;
        }
        for(int i=0;i<n;i++){
            differentPaths[m-1][i] = 1;
        }
        for(int i=m-2;i>=0;i--){
            for(int j=n-2;j>=0;j--){
                differentPaths[i][j] = differentPaths[i+1][j] + differentPaths[i][j+1];
            }
        }
        return differentPaths[0][0];
    }
}
