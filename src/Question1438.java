import java.util.Comparator;
import java.util.PriorityQueue;

public class Question1438 {
    class multiSet{
        PriorityQueue<Integer> smallEndian;
        PriorityQueue<Integer> bigEndian;
        public multiSet(){
            this.smallEndian = new PriorityQueue<>();
            this.bigEndian = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }
            });
        }
        public int getDiff(){
            if(!this.smallEndian.isEmpty()&&!this.bigEndian.isEmpty()){
                return Math.abs(this.smallEndian.peek()-this.bigEndian.peek());
            }else{
                return 0;
            }
        }
        public void add(int x){
            this.smallEndian.add(x);
            this.bigEndian.add(x);
        }
        public void delete(int x){
            this.smallEndian.remove(x);
            this.bigEndian.remove(x);
        }
    }
    public int longestSubarray(int[] nums, int limit) {
        multiSet set = new multiSet();
        int left = 0, right = 0;
        int max_length = 0;
        while(true){
            do{
                set.add(nums[right++]);
            }while(right<nums.length&&set.getDiff()<=limit);
            if(set.getDiff()>limit){//最后一个需要退掉
                max_length = Math.max(max_length, right - left - 1);
                while(left<right&&set.getDiff()>limit){
                    set.delete(nums[left++]);
                }
            }
            if(right==nums.length){//最后一个没退掉，不会比这更长了
                break;
            }
        }
        return max_length;
    }
}
