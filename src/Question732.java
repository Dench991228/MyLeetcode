import java.util.TreeMap;

public class Question732 {
    class MyCalendarThree{

        TreeMap<Integer, Integer> deltas;

        public MyCalendarThree() {
            this.deltas = new TreeMap<>();
        }

        public int book(int start, int end) {
            if(!deltas.containsKey(start))deltas.put(start,0);
            deltas.put(start, deltas.get(start)+1);
            if(!deltas.containsKey(end))deltas.put(end,0);
            deltas.put(end, deltas.get(end)-1);
            int max_number = 0;
            int current = 0;
            for(int i:deltas.keySet()){
                current += deltas.get(i);
                if(current>max_number) max_number = current;
            }
            return max_number;
        }
    }

}
