import java.util.*;

public class Question802 {

    public List<Integer> eventualSafeNodes(int[][] graph) {
        LinkedList<Integer> result = new LinkedList<>();
        HashMap<Integer, TreeSet<Integer>> g = new HashMap<>();
        LinkedList<Integer> queue = new LinkedList<>();//统计没有出边的节点
        int current = 0;
        boolean[] visited = new boolean[graph.length];
        /*建图*/
        for(int[] connections: graph){
            if(connections.length == 0){
                queue.add(current);
                visited[current] = true;
                result.add(current);
            }
            if(!g.containsKey(current))g.put(current, new TreeSet<>());
            for(int dst:connections){//添加链接关系，注意，这里是反着的
                if(!g.containsKey(dst))g.put(dst, new TreeSet<>());
                g.get(dst).add(current);
            }
            current ++;
        }
        /*广度优先搜索*/

        while(!queue.isEmpty()){
            int current_node = queue.pollFirst();
            for(int i: g.get(current_node)){
                if(!visited[i]){
                    visited[i] = true;
                    queue.addLast(i);
                    result.add(i);
                }
            }
        }
        result.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });
        return result;
    }
}
