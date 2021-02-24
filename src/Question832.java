public class Question832 {
    public int[][] flipAndInvertImage(int[][] A) {
        int height = A.length;
        int width = A[0].length;
        for(int i=0;i<height;i++){
            int j;
            for(j=0;j<width/2;j++){
                A[i][j] = -A[i][j]+1;
                A[i][width-1-j] = -A[i][width-1-j]+1;
                A[i][j] ^= A[i][width-1-j];
                A[i][width-1-j] ^= A[i][j];
                A[i][j] ^= A[i][width-1-j];
            }
            if(width%2!=0)A[i][j] = -A[i][j]+1;
        }
        return A;
    }
}
