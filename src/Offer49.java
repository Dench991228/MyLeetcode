import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Offer49 {
    class state implements Comparable<state>{
        private int twoExp;
        private int threeExp;
        private int fiveExp;
        private double result;
        public state(int a, int b, int c){
            twoExp = a;
            threeExp = b;
            fiveExp = c;
            this.result = Math.pow(2, twoExp)*Math.pow(3, threeExp)*Math.pow(5, fiveExp);
        }
        public List<state> expand(){
            LinkedList<state> result = new LinkedList<>();
            if(twoExp+1<=31){
                result.add(new state(twoExp+1, threeExp, fiveExp));
            }
            if(threeExp+1<=31){
                result.add(new state(twoExp, threeExp+1, fiveExp));
            }
            if(fiveExp+1<=31){
                result.add(new state(twoExp, threeExp, fiveExp+1));
            }
            return result;
        }
        @Override
        public int compareTo(state o) {
            return Double.compare(this.result, o.result);
        }
    }
    public int nthUglyNumber(int n) {
        boolean[][][] visited = new boolean[32][32][32];//用来记录某个状态有没有被访问过
        PriorityQueue<state> queue = new PriorityQueue<>();
        state start = new state(0,0,0);
        queue.add(start);
        int current = 0;
        visited[0][0][0] = true;
        state s = start;
        while(current<n){
            s = queue.poll();
            System.out.println(s.result);
            for(state state:s.expand()){
                if(!visited[state.twoExp][state.threeExp][state.fiveExp]){
                    visited[state.twoExp][state.threeExp][state.fiveExp] = true;
                    queue.add(state);
                }
            }
            current++;
        }
        return (int)s.result;
    }
}
