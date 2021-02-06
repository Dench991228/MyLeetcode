public class Question684 {
    /**
     * 合并两个集合
     * @param parent 记录并查集信息
     * @param i
     * @param j
     */
    private void merge(int[] parent, int i, int j){
        int root_i = this.getSet(parent, i);
        int root_j = this.getSet(parent, j);
        parent[root_i] = root_j;
    }

    /**
     * 返回一个元素所属的集合
     * @param parent 记录并查集信息
     * @param i 目标元素
     * @return 所在的集合
     */
    private int getSet(int[] parent, int i){
        if(parent[i]!=i){
            parent[i] = this.getSet(parent, parent[i]);
        }
        return parent[i];
    }
    public int[] findRedundantConnection(int[][] edges) {
        int len = edges.length + 1;
        int[] parent = new int[len];
        int i;
        /*初始化parent数组*/
        for(i=0;i<len;i++){
            parent[i] = i;
        }
        /*遍历一遍全部的边*/
        len--;
        for(i=0;i<len;i++){
            /*二者属于相同集合，那就有问题，把它作为返回值*/
            if(this.getSet(parent,edges[i][0])==this.getSet(parent, edges[i][1])){
                return edges[i];
            }
            /*二者属于不同集合，合并到统一集合*/
            else{
                this.merge(parent, edges[i][0],edges[i][1]);
            }
        }
        return edges[0];
    }
}
