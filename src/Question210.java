import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Question210 {
    /**
     * 进行拓扑排序，然后看最后能不能排出一个合适的顺序来
     * */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] in_degree = new int[numCourses];//各个节点的入度
        HashSet<Integer> has_prerequisite = new HashSet<>();
        ArrayList<LinkedList<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            has_prerequisite.add(i);
            graph.add(new LinkedList<>());
        }
        /*一边找入度为0的节点，一边建邻接表*/
        for(int[] edge:prerequisites){
            has_prerequisite.remove(edge[0]);
            graph.get(edge[1]).addLast(edge[0]);
        }
        /*开始广度优先遍历*/
        int[] order = new int[numCourses];//最终返回的顺序
        LinkedList<Integer> queue = new LinkedList<>(has_prerequisite);
        int current_place = 0;
        while(!queue.isEmpty()){
            int current_vertex = queue.pollFirst();
            for(int i:graph.get(current_vertex)){
                in_degree[i] --;
                if(in_degree[i]==0){
                    queue.addLast(i);
                }
            }
            order[current_place] = current_vertex;
            current_place++;
        }
        return current_place==numCourses?order:null;
    }
}
