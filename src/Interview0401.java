import java.util.ArrayList;
import java.util.LinkedList;

public class Interview0401 {
    /**
     * 判断图中的两个点能不能联通
     * */
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        /*先把边的列表变成邻接表*/
        ArrayList<LinkedList<Integer>> g = new ArrayList<>();
        for(int i=0;i<n;i++){
            g.add(new LinkedList<>());
        }
        for(int[] edge:graph){
            g.get(edge[0]).addLast(edge[1]);
        }
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        visited[start] = true;
        queue.addLast(start);
        while(!queue.isEmpty()){
            int current = queue.pollFirst();
            for(int i:g.get(current)){
                if(i==target)return true;
                if(!visited[i]){
                    visited[i] = true;
                    queue.addLast(i);
                }
            }
        }
        return false;
    }
}
