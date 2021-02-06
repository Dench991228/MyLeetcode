import org.omg.CORBA.INTERNAL;

public class Question1319 {
    /**
     * 并查集操作：获取target对应的集合
     * @param parent 并查集信息
     * @param target 目标数
     * @return
     */
    private int getSet(int[] parent, int target){
        if(parent[target]!=target){
            parent[target] = this.getSet(parent,parent[target]);
        }
        return parent[target];
    }
    private void mergeSet(int[] parent, int setA, int setB){
        int root_a = this.getSet(parent,setA);
        int root_b = this.getSet(parent,setB);
        parent[root_a] = root_b;
    }
    public int makeConnected(int n, int[][] connections) {
        if(connections.length<n-1)return -1;
        int[] parent = new int[n];
        int i;
        /*初始化并查集信息*/
        for(i=0;i<n;i++)parent[i] = i;
        int num_connections = connections.length;
        int nums = 0;//需要拔掉的线的数量
        int connected = 0;//当前已经链接的数量
        for(i=0;i<num_connections;i++){
            if(this.getSet(parent,connections[i][0])==this.getSet(parent,connections[i][1])){
                nums++;
            }
            else{
                this.mergeSet(parent,connections[i][0],connections[i][1]);
                connected++;
            }
        }
        return n-connected-1;
    }
}
