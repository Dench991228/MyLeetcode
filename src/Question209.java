import java.util.LinkedList;

public class Question209 {
    public int minSubArrayLen(int s, int[] nums) {
        if(s==0)return 0;
        int i;
        int len = nums.length;
        /*当前双向队列的长度*/
        int cur_length = 0;
        /*当前双向队列元素的和*/
        int cur_sum = 0;
        /*长度的最小值*/
        int min_length = Integer.MAX_VALUE;
        LinkedList<Integer> deque = new LinkedList<>();
        for(i=0;i<len;i++){
            if(cur_sum + nums[i] >= s){
                deque.addLast(nums[i]);
                cur_sum+=nums[i];
                cur_length++;
                while(cur_sum-deque.peekFirst()>=s){
                    int temp = deque.pollFirst();
                    cur_sum-=temp;
                    cur_length--;
                }
            }
            else{
                deque.addLast(nums[i]);
                cur_sum+=nums[i];
                cur_length++;
            }
            if(cur_sum>=s)min_length=Math.min(cur_length,min_length);
        }
        if(cur_sum<s)return 0;
        return min_length;
    }
}
