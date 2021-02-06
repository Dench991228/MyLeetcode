public class Question64 {

    public int minPathSum(int[][] grid) {
        if(grid.length==0)return 0;
        int height = grid.length;
        int width = grid[0].length;
        int i,j;
        for(i=0;i<height;i++){
            for(j=0;j<width;j++){
                if(j==0&&i==0){
                    continue;
                }
                else if(i==0){
                    grid[i][j]=grid[i][j-1]+grid[i][j];
                }
                else if(j==0){
                    grid[i][j]=grid[i][j]+grid[i-1][j];
                }
                else{
                    grid[i][j]=Math.min(grid[i-1][j],grid[i][j-1])+grid[i][j];
                }
            }
        }
        return grid[height-1][width-1];
    }
}
