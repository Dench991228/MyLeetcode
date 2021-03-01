class NumMatrix {
    private int[][] area_sum;
    /**
     * 初始化area_sum，area_sum[i][j]是matrix[0][0]~matrix[i][j]矩形范围内全部元素的和
     * */
    public NumMatrix(int[][] matrix) {
        int height = matrix.length;
        if(height != 0){
            int width = matrix[0].length;
            if(width != 0){
                area_sum = new int[height][width];
                int current_row_sum = 0;
                for(int i=0;i<height;i++){
                    for(int j=0;j<width;j++){
                        current_row_sum+=matrix[i][j];
                        area_sum[i][j] = (i!=0?area_sum[i-1][j]:0)+current_row_sum;
                    }
                    current_row_sum = 0;
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        System.out.println(area_sum[row1][col1]);
        System.out.println(area_sum[row1][col2]);
        System.out.println(area_sum[row2][col1]);
        System.out.println(area_sum[row2][col2]);
        int sum = this.area_sum[row2][col2];
        if(row1==0&&col1==0){

        }else if(row1==0){
            sum -= this.area_sum[row2][col1-1];
        }else if(col1==0){
            sum -= this.area_sum[row1-1][col2];
        }else{
            sum -= this.area_sum[row1-1][col2]+this.area_sum[row2][col1-1]+this.area_sum[row1-1][col1-1];
        }
        return sum;
    }
}

public class Question304 {
}
