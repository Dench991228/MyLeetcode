import java.util.LinkedList;
import java.util.PriorityQueue;

public class Question847 {
    class state implements Comparable<state>{
        int current_node;
        int visited;//indicate which node are visited
        int length;//indicate the current length of the path
        private state(int c, int v, int l){
            current_node = c;
            visited = v;
            length = l;
        }
        /**
         * visit a node of a state
         * */
        private void visit(int p){
            this.visited|=(1<<p);
        }
        /**
         * tell whether a node was visited
         * */
        private boolean isVisited(int p){
            return (this.visited&(1<<p))!=0;
        }
        /**
         * tell whether the transverse is finished
         * */
        private boolean isComplete(int length){
            int i;
            for(i=0;i<length;i++){
                if((this.visited&(1<<i))==0){
                    return false;
                }
            }
            return true;
        }

        @Override
        public int compareTo(state o) {
            return this.length - o.length;
        }

        @Override
        public String toString() {
            String result = "("+this.current_node+","+this.length+",";
            for(int i=0;i<31;i++){
                result += ((this.visited&(1<<i))==0)?0:1;
            }
            result +=")";
            return result;
        }
    }
    /**
     * first calculate the shortest distance between two vertex through floyd algorithm
     * then calculate the shortest distance to transverse the new complete graph
     * */
    public int shortestPathLength(int[][] graph) {
        /*generate the neighbor matrix*/
        int[][] matrix = new int[graph.length][graph.length];
        /*initialize the matrix*/
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++){
                matrix[i][j] = 0x07000000;
            }
        }
        /*initialize the matrix with weight*/
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[i].length;j++){
                matrix[i][graph[i][j]] += 1;
                matrix[graph[i][j]][i] = 1;
            }
        }
        /*floyd algorithm*/
        for(int maximum_median_vertex = 0;maximum_median_vertex<graph.length;maximum_median_vertex++){
            for(int i=0;i<graph.length;i++){
                for(int j=0;j<graph.length;j++){
                    if(matrix[i][maximum_median_vertex]+matrix[maximum_median_vertex][j]<matrix[i][j]){
                        matrix[i][j] = matrix[i][maximum_median_vertex] + matrix[maximum_median_vertex][j];
                    }
                }
            }
        }
        int min = 0x07000000;
        for(int start = 0; start<graph.length; start++){
            System.out.println(start);
            state start_state = new state(start, 0,0);
            start_state.visit(start);
            PriorityQueue<state> queue = new PriorityQueue<>();
            queue.add(start_state);
            state current_state = start_state;
            while(!queue.isEmpty()&&!current_state.isComplete(matrix.length)){
                current_state = queue.poll();
                for(int i=0;i<matrix.length;i++){
                    if(!current_state.isVisited(i)){
                        state expanded = new state(i, current_state.visited, current_state.length+matrix[current_state.current_node][i]);
                        queue.add(expanded);
                        expanded.visit(i);
                    }
                }
            }
            min = Math.min(min, current_state.length);
        }
        return min;
    }
}
