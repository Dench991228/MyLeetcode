import java.util.HashMap;
import java.util.HashSet;

public class Question947 {
    // 任意两个点，只要x或者y轴坐标相同，就有一条无向边
    // 有几个强联通分量，就剩几颗石子
    private class DisjointSet{
        HashMap<Integer, Integer> parent;
        int count;

        public DisjointSet(){
            this.parent = new HashMap<>();
            this.count = 0;
        }

        public int find(int x){
            if(!this.parent.containsKey(x)){
                this.parent.put(x,x);
                this.count++;
                return x;
            }
            if(this.parent.get(x)!=x){
                this.parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        public void merge(int i, int j){
            int root_i = this.find(i);
            int root_j = this.find(j);
            if(root_i!=root_j){
                this.parent.put(root_i, root_j);
                this.count--;
            }
        }
    }
    public int removeStones(int[][] stones){
        DisjointSet djs = new DisjointSet();
        for(int[] s:stones){
            djs.merge(s[0]+10001, s[1]);
        }
        return stones.length-djs.count;
    }
}
