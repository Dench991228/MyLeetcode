import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Question841 {
    /**
     * 直接广度优先算法解决问题
     * */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        LinkedList<Integer> queue = new LinkedList<>();
        int num_vertices = rooms.size();
        int num_confirmed = 0;
        boolean[] visited = new boolean[num_vertices];
        queue.add(0);
        visited[0] = true;
        num_confirmed++;
        while(!queue.isEmpty()){
            int current_node = queue.pollFirst();
            for(int i:rooms.get(current_node)){
                if(!visited[i]){
                    visited[i] = true;
                    num_confirmed++;
                    queue.addLast(i);
                }
            }
        }
        return num_confirmed==num_vertices;
    }
}
