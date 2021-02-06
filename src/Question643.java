import java.util.LinkedList;
import java.util.PriorityQueue;

public class Question643 {
    /**
     * 计算nums中连续K个数的最大平均值
     * @param nums rt
     * @param k rt
     * @return 最大的平均数
     * */
    public double findMaxAverage(int[] nums, int k) {
        LinkedList<Integer> current_number = new LinkedList<>();
        int current_sum = 0;
        double max_sum = Integer.MIN_VALUE;
        for(int num:nums){
            if(current_number.size()!=k){
                current_number.addLast(num);
                current_sum += num;
                if(current_number.size()==k){
                    max_sum = Math.max(current_sum, max_sum);
                }
            }else{
                current_sum -= current_number.pollFirst();
                current_number.addLast(num);
                current_sum += num;
                max_sum = Math.max(current_sum, max_sum);
            }
        }
        PriorityQueue<Integer> test = new PriorityQueue<>();
        return max_sum/k;
    }
}
