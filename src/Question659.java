import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Question659 {
    /**
     * 建立一个哈希表，键是最大值，值是最小队列，每一个连续序列的长度，顶端是最小堆
     * */
    public boolean isPossible(int[] nums) {
        HashMap<Integer, PriorityQueue<Integer>> arrays = new HashMap<>();
        for(int i:nums){
            System.out.println(i+" was about to calculate");
            System.out.println(arrays);
            if(!arrays.containsKey(i-1)||arrays.get(i-1).isEmpty()){//如果没有以i-1结尾的连续序列
                if(!arrays.containsKey(i))arrays.put(i, new PriorityQueue<>());
                arrays.get(i).add(1);
            }else{
                int current_length = arrays.get(i-1).poll();
                if(!arrays.containsKey(i))arrays.put(i, new PriorityQueue<>());
                arrays.get(i).add(current_length+1);
            }
            System.out.println(arrays);
            System.out.println();
        }
        for(int i:arrays.keySet()){
            if(!arrays.get(i).isEmpty()&&arrays.get(i).poll()<3)return false;
        }
        return true;
    }
}
