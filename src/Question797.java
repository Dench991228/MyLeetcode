import java.util.*;

public class Question797 {
    private void modifyResult(List<List<Integer>> result, Deque<Integer> path){
        ArrayList<Integer> new_path = new ArrayList<>();
        LinkedList<Integer> cache = new LinkedList<>();
        while(!path.isEmpty()){
            int temp = path.pollFirst();
            new_path.add(temp);
            cache.add(temp);
        }
        for(Integer i:cache){
            path.addLast(i);
        }
        result.add(new_path);
    }
    private void runPath(int[][] graph,List<List<Integer>> result, Deque<Integer> cur_path, int cur_pos){
        if(cur_pos == graph.length-1){//找到了最终的节点，把当前记录的路径加上去
            modifyResult(result,cur_path);
        }
        else{
            int len = graph[cur_pos].length;
            int i;
            for(i=0;i<len;i++){
                cur_path.addLast(graph[cur_pos][i]);
                runPath(graph,result,cur_path,graph[cur_pos][i]);
                cur_path.pollLast();
            }
        }
    }
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        Deque<Integer> cur_path = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        cur_path.push(0);
        this.runPath(graph,result,cur_path,0);
        return result;
    }
}
