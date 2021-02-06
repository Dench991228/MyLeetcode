public class Question685 {
    /*考虑所有的问题边的可能性*/
    /*可能性1，问题边最终导致成环，并且是连接到本来的树根上
    * 很好解决：记录所有点的核心根节点，问题边链接的时候，会发现前后两点的根节点一样
    * */
    /*可能性2，问题边导致有一条边入度为2
    * 发现之后，把剩下的全部都构建完
    * 找出来另一条边，把它删掉（让后继节点parent是自己）
    * 分别尝试两条边，如果有一条边出现父节点等于目标点，那就删掉它
    * 否则删掉靠后出现的一条边
    * */

    /**
     * 合并两个集合
     * @param parent 并查集相关
     * @param i
     * @param j
     */
    private void merge(int[] parent, int i, int j){
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
    private int getSet(int[] parent, int i){
        if(parent[i]!=i){
            parent[i] = this.getSet(parent, parent[i]);
        }
        return parent[i];
    }
    public int[] findRedundantDirectedConnection(int[][] edges) {
        /*记录并查集信息*/
        int[] parent = new int[edges.length+1];
        /*记录入度信息*/
        int[] in_degree = new int[edges.length+1];
        /*初始化并查集*/
        int i;
        for(i=0;i<parent.length;i++){
            parent[i] = i;
        }
        int[] makeRing = new int[2];//导致成环的边
        int[] makeTwo = new int[2];//导致入度为2的边
        int[] makeTwoRing = new int[2];//导致入度为2且成环的边
        boolean hasTwo = false;//有没有入度为2的点
        boolean hasRing = false;
        /*开始遍历各条边*/
        /*有环，且没有入度为2的边，把成环的边扔掉*/
        /*有入度为2的边，而且没有成环，扔掉导致入度为2的边*/
        /*有入度为2的边，且成环，抛掉导致成环，且导致入度为2的那条边*/
        for(i=0;i<edges.length;i++){
            int f = edges[i][0];
            int s = edges[i][1];
            in_degree[s]++;
            boolean isMerge = true;
            /*判断入度是不是2*/
            if(in_degree[s]==2){
                makeTwo = edges[i];
                hasTwo = true;
                isMerge = false;
            }
            /*判断成环的事情*/
            if(this.getSet(parent, s)==this.getSet(parent, f)){
                makeRing = edges[i];
                hasRing = true;
            }
            if(isMerge)this.merge(parent,f,s);
        }
        if(hasRing&&!hasTwo){
            return makeRing;
        }
        else if(hasTwo&&!hasRing){
            return makeTwo;
        }
        else{
            int[] another = new int[2];//另一条边
            for(i=0;i<edges.length;i++){
                if(edges[i][1] == makeTwo[1]&&edges[i][0]!=makeTwo[0])another=edges[i];
            }
            /*判断第二条边两侧的节点是不是一个*/
            if(this.getSet(parent, makeTwo[1])==this.getSet(parent, makeTwo[0])){
                return makeTwo;
            }
            else{
                return another;
            }
        }
    }
}
