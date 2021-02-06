import java.util.LinkedList;

public class Question674 {
    /**
     * 寻找nums中最长的连续递增子序列
     * 采用单调栈解决问题
     * */
    public int findLengthOfLCIS(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<>();
        int current_length = 0;
        int max_length = 0;
        for(int i:nums){
            if(stack.size()==0){//当前栈中没有东西
                stack.addLast(i);
                current_length += 1;
            }else if(stack.peekLast()>=i){
                while(!stack.isEmpty()){
                    stack.pollLast();
                }
                current_length = 1;
            }else{
                stack.addLast(i);
                current_length ++;
            }
            max_length = Math.max(max_length, current_length);
        }
        return max_length;
    }
}
