import java.util.LinkedList;

public class Question239 {
    /**
     * 把一个整数加入到单调递减栈中
     * @param stack 单调递减栈，装的是区间极大值的下标
     * @param nums 数组
     * @param new_num 新的数的下标
     * @param width 窗口宽度
     */
    private void pushStack(LinkedList<Integer> stack, int[] nums, int new_num, int width){
        /*先弹出不在窗口中的*/
        while(!stack.isEmpty()&&stack.peekFirst()<new_num-width+1){
            stack.pollFirst();
        }
        /*在从后往前弹出比nums[new_num]小的*/
        while(!stack.isEmpty()&&nums[stack.peekLast()]<nums[new_num]){
            stack.pollLast();
        }
        stack.addLast(new_num);
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> descend_stack = new LinkedList<>();

        /*先把前k个的最大值找出来*/
        int i, len = nums.length;
        /*当前到第几个窗口*/
        int cur_window = 0;
        int[] result = new int[len-k+1];
        for(i=0;i<k;i++){
            this.pushStack(descend_stack,nums,i,k);
        }
        result[cur_window] = nums[descend_stack.peekFirst()];
        cur_window++;
        while(i<len){
            this.pushStack(descend_stack,nums,i,k);
            i++;
            result[cur_window] = nums[descend_stack.peekFirst()];
            cur_window++;
        }
        return result;
    }
}
