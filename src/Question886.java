import java.util.HashMap;
import java.util.LinkedList;

public class Question886 {
    /**
     * 判断一个强联通分量是不是二分图
     * @param matrix 邻接矩阵
     * @param visited 用来记录各个点是不是访问过，方便后面循环判断强联通分量是不是二分图
     * @param s 这个强联通分量的一个点
     * */
    private boolean isBiPartition(boolean[][] matrix, boolean[] visited, int s){
        int num_vertices = matrix.length;//整张图一共有多少个节点
        LinkedList<Integer> queue = new LinkedList<>();//广度优先时候的队列
        HashMap<Integer, Boolean> color = new HashMap<>();//本强联通分量内各个节点的颜色
        queue.addLast(s);
        visited[s] = true;
        color.put(s, true);
        while(!queue.isEmpty()){
            int current = queue.pollFirst();
            int i;
            for(i=0;i<num_vertices;i++){
                if(matrix[current][i]){
                    if(visited[i]){//这个点访问过，检查颜色情况
                        if(color.get(i)==color.get(current))return false;//如果二者相邻，而且还被染了相同的颜色，那就GG
                    }
                    else{//这个点没访问过，设置相反颜色，标记为已访问，加入队列
                        visited[i] = true;
                        color.put(i, !color.get(current));
                        queue.addLast(i);
                    }
                }
            }
        }
        return true;
    }
    // 构建邻接矩阵
    // 如果两个人互相不喜欢，那么matrix[i][j]=1
    // 每一个分量都是二分图
    public boolean possibleBipartition(int N, int[][] dislikes) {
        boolean[][] matrix = new boolean[N][N];
        int i;
        int num_pairs = dislikes.length;
        for(i=0;i<num_pairs;i++){
            int x = dislikes[i][0]-1;
            int y = dislikes[i][1]-1;
            matrix[x][y] = true;
            matrix[y][x] = true;
        }
        boolean[] visited = new boolean[N];
        for(i=0;i<N;i++){
            if(!visited[i]){
                boolean flag = isBiPartition(matrix, visited, i);
                if(!flag)return false;
            }
        }
        return true;
    }
}
