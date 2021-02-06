import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

public class Question785 {
    private boolean test(HashSet<Integer> zero, HashSet<Integer> one, int cur_pos, int cur_group, int[][] graph, ArrayDeque<Integer> nodes, ArrayDeque<Integer> group){
        int i, len = graph[cur_pos].length;
        for(i=0;i<len;i++){
            int temp = graph[cur_pos][i];
            if(cur_group==1&&one.contains(temp))return false;
            else if(cur_group==0&&zero.contains(temp))return false;
            else{
                if(cur_group==0&&!one.contains(temp)){
                    nodes.addLast(temp);
                    group.addLast(1);
                    one.add(temp);
                }
                else if(cur_group==1&&!zero.contains(temp)){
                    nodes.addLast(temp);
                    group.addLast(0);
                    zero.add(temp);
                }
            }
        }
        return true;
    }
    public boolean isBipartite(int[][] graph) {
        if(graph.length==2)return true;
        if(graph.length==1)return false;
        HashSet<Integer> group_zero = new HashSet<>();
        HashSet<Integer> group_one = new HashSet<>();
        ArrayDeque<Integer> nodes = new ArrayDeque<>();
        ArrayDeque<Integer> group = new ArrayDeque<>();
        nodes.addLast(0);
        group.addLast(0);
        group_zero.add(0);
        while(!nodes.isEmpty()){
            int cur_pos = nodes.pollFirst();
            int cur_group = group.pollFirst();
            if(!test(group_zero,group_one,cur_pos,cur_group,graph,nodes,group))return false;
        }
        return group_one.size()+group_zero.size()==graph.length;
    }
}
