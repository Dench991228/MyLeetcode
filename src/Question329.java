public class Question329 {
    /**
     * 深度优先搜索，从一个点开始，标记所有的从此处开始的递增路径
     * 如果周围所有的都比自己小，那么这个点的path就是1
     * 否则就是周围比它大的节点，的最大的加一
     * @param matrix 待寻找最长递增路径的矩阵
     * @param path 各个节点开始，最长的递增路径，还没有研究过的为0
     * @param v 开始节点的行数
     * @param h 开始节点的列数
     */
    private void dfs(int[][] matrix, int[][] path, int v, int h){
        int height = matrix.length;
        int width = matrix[0].length;
        int num_less = 0;
        int surrounds = 0;
        if(v>0){//考虑在它上面的节点
            surrounds++;
            if(matrix[v-1][h]<=matrix[v][h])num_less++;
            else{
                if(path[v-1][h]==0)dfs(matrix,path,v-1,h);
                path[v][h] = Math.max(path[v][h],path[v-1][h]+1);
            }
        }
        if(h>0){//考虑左边的节点
            surrounds++;
            if(matrix[v][h-1]<=matrix[v][h])num_less++;
            else{
                if(path[v][h-1]==0)dfs(matrix,path,v,h-1);
                path[v][h] = Math.max(path[v][h],path[v][h-1]+1);
            }
        }
        if(h<width-1){//考虑右边的节点
            surrounds++;
            if(matrix[v][h+1]<=matrix[v][h])num_less++;
            else{
                if(path[v][h+1]==0)dfs(matrix,path,v,h+1);
                path[v][h] = Math.max(path[v][h],path[v][h+1]+1);
            }
        }
        if(v<height-1){//考虑下面的节点
            surrounds++;
            if(matrix[v+1][h]<=matrix[v][h])num_less++;
            else{
                if(path[v+1][h]==0)dfs(matrix,path,v+1,h);
                path[v][h] = Math.max(path[v][h],path[v+1][h]+1);
            }
        }
        if(num_less==surrounds){
            path[v][h] = 1;
        }
    }
    public int longestIncreasingPath(int[][] matrix) {
        int i,j;
        int max = 0;
        int height = matrix.length;
        int width = matrix[0].length;
        int[][] path = new int[height][width];
        for(i=0;i<height;i++){
            for(j=0;j<width;j++){
                if(path[i][j]==0)this.dfs(matrix,path,i,j);
                max = Math.max(max,path[i][j]);
            }
        }
        return max;
    }
}
