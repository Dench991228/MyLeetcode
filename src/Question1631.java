public class Question1631 {
    /**
     * 合并两个集合
     * @param parent 并查集相关
     * @param i 集合i
     * @param j 集合j
     */
    private void merge(int[][] parent, int i, int j){
        int root_i = this.getSet(parent, i);
        int root_j = this.getSet(parent, j);
        parent[root_j] = root_i;
    }

    /**
     * 得到一个节点所属的集合
     * @param parent 并查集信息
     * @param i 目标节点
     * @return 所属的集合
     */
    private int getSet(int[][] parent, int i, int j){
        if(parent[i][j]!=i){
            parent[i][j] = this.getSet(parent, parent[i][j]);
        }
        return parent[i];
    }
    public int minimumEffortPath(int[][] heights) {

    }
}
