public class Question867 {
    public int[][] transpose(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        int[][] result = new int[width][height];
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }
}
