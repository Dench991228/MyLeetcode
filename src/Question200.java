import java.util.ArrayDeque;
import java.util.LinkedList;

public class Question200 {
    private void bfs(int i, int j, char[][] grid){
        LinkedList<Integer> v_axis = new LinkedList<>();
        LinkedList<Integer> h_axis = new LinkedList<>();
        v_axis.addLast(i);
        h_axis.addLast(j);
        grid[i][j]='0';
        while(!v_axis.isEmpty()){
            int v = v_axis.pollFirst();
            int h = h_axis.pollFirst();
            if(v!=0&&grid[v-1][h]=='1'){
                v_axis.addLast(v-1);
                h_axis.addLast(h);
                grid[v-1][h] = '0';//这个点已经访问过了
            }
            if(h!=0&&grid[v][h-1]=='1'){
                v_axis.addLast(v);
                h_axis.addLast(h-1);
                grid[v][h-1] = '0';//这个点已经访问过了
            }
            if(v<grid.length-1&&grid[v+1][h]=='1'){
                v_axis.addLast(v+1);
                h_axis.addLast(h);
                grid[v+1][h] = '0';//这个点已经访问过了
            }
            if(h<grid[v].length-1&&grid[v][h+1]=='1'){
                v_axis.addLast(v);
                h_axis.add(h+1);
                grid[v][h+1] = '0';//这个点已经访问过了
            }
        }
    }
    public int numIslands(char[][] grid) {
        if(grid.length==0||grid[0].length==0)return 0;
        int num_island = 0;
        int height = grid.length, width = grid[0].length;
        int i,j;
        for(i=0;i<height;i++){
            for(j=0;j<width;j++){
                if(grid[i][j]=='1'){
                    this.bfs(i,j,grid);
                    num_island++;
                }
            }
        }
        return num_island;
    }
}
