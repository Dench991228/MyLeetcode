import java.util.LinkedList;
import java.util.List;

public class Question1557 {
    /**
     * 从有向无环图中选取若干节点，要求这些节点出发可以抵达所有节点
     * 本质上是求入度为0的节点
     * */
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        boolean[] reachable = new boolean[n];
        for(List<Integer> edge:edges){
            reachable[edge.get(1)] = true;
        }
        List<Integer> result = new LinkedList<>();
        for(int i=0; i<n;i++){
            if(!reachable[i]){
                result.add(i);
            }
        }
        return result;
    }
}
