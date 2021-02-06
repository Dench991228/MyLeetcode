import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Question207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        /*邻接表，用来标识整张图*/
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();//每个点的后继节点
        int[] vertex_indegree = new int[numCourses];//记录每个点的入度

        /*表的初始化工作*/
        int i;
        //初始化列表，最初的时候所有点的入度都是0
        for(i=0;i<numCourses;i++){
            graph.add(new ArrayList<Integer>());
        }
        //初始化边的关系
        for(i=0;i<prerequisites.length;i++){
            graph.get(prerequisites[i][0]).add(prerequisites[i][1]);
            vertex_indegree[prerequisites[i][1]]++;
        }

        /*找出所有的入度为0的点，准备广度优先搜索*/
        LinkedList<Integer> bfsQueue = new LinkedList<>();//bfs队列
        int numLeaved = 0;//已经敲定的点
        for(i=0;i<numCourses;i++){
            if(vertex_indegree[i]==0){
                bfsQueue.add(i);
                numLeaved++;
            }
        }
        if(numLeaved==0)return false;
        while(!bfsQueue.isEmpty()){
            int selected = bfsQueue.pollFirst();
            ArrayList<Integer> neighbors = graph.get(selected);
            for(Integer e:neighbors){
                vertex_indegree[e]--;
                if(vertex_indegree[e]==0){
                    numLeaved++;
                    bfsQueue.add(e);
                }
            }
        }
        return numLeaved==numCourses;
    }
}
