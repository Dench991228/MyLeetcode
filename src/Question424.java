import java.util.PriorityQueue;

public class Question424 {
    static class node implements  Comparable<node>{
        public char c;
        public int frequency;
        public node(char character, int frequency){
            this.c = character;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(node o) {
            return - this.frequency + o.frequency;
        }

        @Override
        public String toString() {
            return "("+c+","+frequency+")";
        }
    }
    public int characterReplacement(String s, int k) {
        PriorityQueue<node> stat = new PriorityQueue<>();
        int left = 0, right = 0, length = s.length();
        int max_length = 0;
        int current_modify = 0;
        node[] nodes = new node[26];
        for(char c='A';c<='Z';c++){
            nodes[c-'A'] = new node(c, 0);
            stat.add(nodes[c-'A']);
        }
        while(right<length){
            while(right<length&&current_modify<=k){
                node e = nodes[s.charAt(right++)-'A'];
                e.frequency++;
                stat.remove(e);
                stat.add(e);
                assert stat.peek() != null;
                current_modify = (right - left)-stat.peek().frequency;
            }
            if(current_modify>k){
                max_length = Math.max(max_length, right - left);
                while(left<right){
                    node e = nodes[s.charAt(left) - 'A'];
                    e.frequency --;
                    stat.remove(e);
                    stat.add(e);
                    left++;
                    assert stat.peek() != null;
                    current_modify = (right - left)-stat.peek().frequency;
                    if(current_modify<=k)break;
                }
            }
            if(right>=length){
                break;
            }
        }
        max_length = Math.max(max_length, right - left);
        return max_length;
    }
}
