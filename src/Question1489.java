import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
class edge{
    int from;
    int to;
    int weight;
    edge(){
        super();
    }
    public edge(int f, int t, int w){
        from = f;
        to = t;
        weight = w;
    }
}
public class Question1489 {
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
    /**
     * 删除掉一条边之后计算最小生成树的权值和
     * @param num_vertices 节点数量
     * @param edges 边集合（按照权值排序）
     * @param exempt 排出的边的下标
     * @return 权值，如果是-1就意味着无法生成最小生成树
     */
    private int getKruscal(int num_vertices, ArrayList<edge> edges, int exempt){
        /*记录并查集信息*/
        int[] parent = new int[num_vertices];
        int i;
        /*初始化并查集*/
        for(i=0;i<num_vertices;i++){
            parent[i] = i;
        }
        int num_edges = edges.size();//边的数量
        int total_weight = 0;//总权值，如果是负数，那就是无法生成mst
        int num_counted = 0;//当前mst的边数
        for(i=0;i<num_edges;i++){
            if(num_counted==num_vertices-1)break;
            else if(i!=exempt&&this.getSet(parent, edges.get(i).from)!=this.getSet(parent,edges.get(i).to)){
                this.merge(parent,edges.get(i).from,edges.get(i).to);
                num_counted++;
                total_weight+=edges.get(i).weight;
            }
        }
        if(num_counted!=num_vertices-1)return -1;
        return total_weight;
    }
    private boolean isPseudoCritical(int num_vertices, ArrayList<edge> edges, int target, int min){
        /*记录并查集信息*/
        int[] parent = new int[num_vertices];
        int i;
        /*初始化并查集*/
        for(i=0;i<num_vertices;i++){
            parent[i] = i;
        }
        int num_edges = edges.size();//边的数量
        int total_weight = 0;//总权值，如果是负数，那就是无法生成mst
        int num_counted = 0;//当前mst的边数
        /*先把target边放进去*/
        this.merge(parent,edges.get(target).from,edges.get(target).to);
        num_counted++;
        total_weight+=edges.get(target).weight;
        for(i=0;i<num_edges;i++){
            if(num_counted==num_vertices-1)break;
            else if(this.getSet(parent, edges.get(i).from)!=this.getSet(parent,edges.get(i).to)){
                this.merge(parent,edges.get(i).from,edges.get(i).to);
                num_counted++;
                total_weight+=edges.get(i).weight;
            }
        }
        if(num_counted!=num_vertices-1)return true;
        else return total_weight <= min;
    }
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        ArrayList<edge> list_edge = new ArrayList<>();
        HashMap<edge,Integer> edge_map = new HashMap<>();
        int i;
        for(i=0;i<edges.length;i++){
            edge e = new edge(edges[i][0],edges[i][1],edges[i][2]);
            list_edge.add(e);
            edge_map.put(e,i);
        }
        list_edge.sort(new Comparator<edge>() {
            @Override
            public int compare(edge o1, edge o2) {
                if(o1.weight<o2.weight)return -1;
                else if(o1.weight>o2.weight)return 1;
                else return 0;
            }
        });
        int num_edges = list_edge.size();
        int minSum = this.getKruscal(n,list_edge,num_edges);
        ArrayList<Integer> keys = new ArrayList<>();
        ArrayList<Integer> ignore = new ArrayList<>();
        for(i=0;i<num_edges;i++){
            int curSum = this.getKruscal(n,list_edge,i);
            if(curSum==-1){//去掉这条边，就不能形成mst，所以肯定是关键边
                keys.add(edge_map.get(list_edge.get(i)));
            }
            else if(curSum>minSum){//去掉这条边，mst全职变大，肯定是关键边
                ignore.add(edge_map.get(list_edge.get(i)));
            }
            else{//去掉这条边没影响，需要判断是不是最小生成树的一边
                if(this.isPseudoCritical(n,list_edge,i,minSum))keys.add(edge_map.get(list_edge.get(i)));
            }
        }
        ArrayList<List<Integer>> result = new ArrayList<>();
        result.add(keys);
        result.add(ignore);
        return result;
    }
}
