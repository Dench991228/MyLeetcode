import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Question743 {

    class state implements Comparable<state>{
        int src;
        int dst;
        int weight;

        state(int s, int d, int w){
            src = s;
            dst = d;
            weight = w;
        }

        @Override
        public int compareTo(state o) {
            return weight - o.weight;
        }

        @Override
        public String toString() {
            return "("+src+","+dst+","+weight+")";
        }
    }
    /**
     * 使用迪杰斯特拉算法+邻接表
     * */
    public int networkDelayTime(int[][] times, int N, int K) {
        ArrayList<LinkedList<state>> graph = new ArrayList<>();
        int[] shortest_distance = new int[N+1];
        graph.add(new LinkedList<>());
        for(int i=0;i<N;i++){
            shortest_distance[i+1] = 0x07000000;
            graph.add(new LinkedList<>());
        }
        /*构架邻接表*/
        for(int[] edge:times){
            graph.get(edge[0]).addLast(new state(edge[0], edge[1], edge[2]));
        }
        shortest_distance[K] = 0;
        PriorityQueue<state> dijkstra = new PriorityQueue<>();
        dijkstra.add(new state(0, K, 0));
        int num_confirmed = 0;
        boolean[] confirmed = new boolean[N+1];
        while(!dijkstra.isEmpty()&&num_confirmed!=N){
            state current_state = dijkstra.poll();
            if(!confirmed[current_state.dst]){
                System.out.println(current_state.dst+" was confirmed");
                confirmed[current_state.dst] = true;
                num_confirmed++;
                shortest_distance[current_state.dst] = current_state.weight;
                System.out.println("shortest distance to "+current_state.dst+" is "+ shortest_distance[current_state.dst]);
                for(state s: graph.get(current_state.dst)){
                    dijkstra.add(new state(current_state.dst, s.dst, s.weight+shortest_distance[current_state.dst]));
                }
            }
        }
        int max_delay = 0;
        for(int i=1;i<=N;i++){
            max_delay = Math.max(max_delay, shortest_distance[i]);
        }
        return num_confirmed==N?max_delay:-1;
    }
}
