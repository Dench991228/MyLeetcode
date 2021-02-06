public class Question547 {
    //使用并查集解决这个问题

    /**
     * 把i和j所属的朋友圈合并
     * @param i
     * @param j
     * @param parent 记录并查集相关信息的集合
     */
    private void merge(int[] parent, int i, int j){
        int root_i = this.getSet(parent, i);
        int root_j = this.getSet(parent, j);
        parent[root_i] = root_j;
    }

    /**
     * 返回x所属的集合，使用状态压缩
     * @param parent 记录并查集信息
     * @param x 目标值
     * @return 所属的集合
     */
    private int getSet(int[] parent, int x){
        if(parent[x] != x){
            parent[x] = getSet(parent, parent[x]);
        }
        return parent[x];
    }
    public int findCircleNum(int[][] M) {
        int num_people = M.length;
        int[] parent = new int[num_people];
        int num_moments = num_people;
        int i,j;
        /*初始化parent数组*/
        for(i=0;i<num_people;i++){
            parent[i] = i;
        }
        /*遍历整个关系表，因为是对称的，所以只遍历半边*/
        for(i=0;i<num_people;i++){
            for(j=0;j<=i;j++){
                if(M[i][j]==1&&this.getSet(parent,i)!=this.getSet(parent,j)){
                    num_moments--;
                    this.merge(parent,i,j);
                }
            }
        }
        return num_moments;
    }
}
