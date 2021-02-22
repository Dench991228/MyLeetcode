public class Question766 {
    /**
     * 判断一个矩阵是不是从左上到右下的所有的对角线，全部元素都一样
     * 本质上就是判断每一个元素是不是和左上角的元素相等
     * */
    public boolean isToeplitzMatrix(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        for(int r = 0;r<height;r++){
            for(int c = 0;c<width;c++){
                if(r-1>=0&&c-1>=0&&matrix[r-1][c-1]!=matrix[r][c]){
                    return false;
                }
            }
        }
        return true;
    }
}
