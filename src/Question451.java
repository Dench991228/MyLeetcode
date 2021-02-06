import java.util.HashMap;
import java.util.PriorityQueue;

public class Question451 {
    class Node implements Comparable<Node>{
        char character;
        int frequency;

        public Node(char c, int f){
            this.character = c;
            this.frequency = f;
        }

        @Override
        public int compareTo(Node o) {
            return o.frequency - this.frequency;
        }
    }
    public String frequencySort(String s) {
        HashMap<Character, Integer> frequency_statistic = new HashMap<>();
        int i, len = s.length();
        for(i=0;i<len;i++){
            if(!frequency_statistic.containsKey(s.charAt(i))){
                frequency_statistic.put(s.charAt(i),1);
            }else{
                frequency_statistic.put(s.charAt(i), frequency_statistic.get(s.charAt(i))+1);
            }
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(char c: frequency_statistic.keySet()){
            pq.add(new Node(c, frequency_statistic.get(c)));
        }
        StringBuilder result = new StringBuilder();
        while(!pq.isEmpty()){
            Node current = pq.poll();
            int f = current.frequency;
            for(i=0; i<f;i++){
                result.append(current.character);
            }
        }
        return result.toString();
    }
}
