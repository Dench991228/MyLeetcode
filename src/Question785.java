import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Question785 {
    /**
     * 判断graph给出的图是不是二分图，使用广度优先遍历法，尝试给每一个节点一个组，看看行不行
     * @param graph graph[i]表示与i节点相邻的点，边都是无向的
     * */
    public boolean isBipartite(int[][] graph) {
        if(graph.length==2||graph.length==1)return true;
        boolean[] visited = new boolean[graph.length];//用来记录各个节点是不是被遍历过了
        boolean[] belonging = new boolean[graph.length];//用来记录各个节点属于哪一个部分
        for(int i=0; i<graph.length;i++){
            if(!visited[i]){//未被访问，开始广度优先遍历
                LinkedList<Integer> queue = new LinkedList<>();
                queue.addLast(i);
                belonging[i] = false;
                visited[i] = true;
                while(!queue.isEmpty()){
                    int current = queue.pollFirst();
                    for(int t:graph[current]){
                        if(visited[t]&&belonging[t]==belonging[current]){
                            return false;
                        }
                        if(!visited[t]){
                            queue.addLast(t);
                            belonging[t] = !belonging[current];
                            visited[t] = true;
                        }
                    }
                }
            }
        }
        return true;
    }
}
