import java.util.Comparator;
import java.util.PriorityQueue;

public class Question1584 {
    /**
     * @author Dench
     * @version 点与点之间的边
     * */
    private class Edge{
        int src;
        int dst;
        int weight;
        public Edge(int s, int e, int w){
            src = s;
            dst = e;
            weight = w;
        }
        @Override
        public String toString(){
            return "("+src+","+dst+","+weight+")";
        }
    }

    /**
     * 根据输入的点，生成距离矩阵
     * @param points  输入的点，每一个元素是二元组
     * @return 距离矩阵
     * */
    private PriorityQueue<Edge> disMatrix(int[][] points){
        /*最终返回的距离矩阵*/
        PriorityQueue<Edge> result = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        });
        for(int i=0; i<points.length; i++){
            for(int j=i-1; j>=0; j--){
                int manhattan_dist = Math.abs(points[i][0]-points[j][0])+Math.abs(points[i][1]-points[j][1]);
                Edge e = new Edge(i,j,manhattan_dist);
                result.add(e);
                e = new Edge(j,i,manhattan_dist);
                result.add(e);
            }
        }
        return result;
    }

    /**
     * 在并查集中获得一个节点所属的集合
     * @param parent 存放并查集信息的数组
     * @param x 被查询的节点
     * @return 返回值
     * */
    private int getParent(int[] parent, int x){
        if(parent[x]!=x){
            parent[x] = getParent(parent, parent[x]);
        }
        return parent[x];
    }

    /**
     * 合并两个并查集
     * @param parent 并查集分配情况
     * @param x x集合
     * @param y y集合
     * */
    private void merge(int[] parent, int x, int y){
        int root_x = getParent(parent, x);
        int root_y = getParent(parent, y);
        parent[root_y] = root_x;
    }

    /**
     * 点和点之间的距离是曼哈顿距离，返回这些点的最小生成树的权值和
     * @param points 点的数组
     * @return 最小生成树的权值和
     * */
    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<Edge> pq = this.disMatrix(points);
        int[] parent = new int[points.length];
        for(int i=0; i<points.length;i++){
            parent[i] = i;
        }
        int sum = 0;
        while(!pq.isEmpty()){
            Edge current = pq.poll();
            if(getParent(parent, current.src)!=getParent(parent, current.dst)){
                merge(parent, current.src, current.dst);
                sum+=current.weight;
            }
        }
        return sum;
    }
}
