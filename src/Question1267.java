import java.util.HashMap;

public class Question1267 {
    public int countServers(int[][] grid) {
        int width = grid[0].length, height = grid.length;
        int[] column = new int[width];
        int[] row = new int[height];
        for(int r=0; r<height;r++){
            for(int c=0;c<width;c++){
                if(grid[r][c]==1){
                    row[r] += 1;
                    column[c] += 1;
                }
            }
        }
        for(int i:column){
            System.out.print(i+" ");
        }
        System.out.println();
        for(int i:row){
            System.out.print(i+" ");
        }
        System.out.println();
        int count = 0;
        for(int r=0; r<height;r++){
            for(int c=0;c<width;c++){
                if(grid[r][c]==1&&(row[r]!=1||column[c]!=1)){
                    row[r] += 1;
                    column[c] += 1;
                }
            }
        }
        return count;
    }
}
