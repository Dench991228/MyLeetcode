import java.util.Arrays;

public class Question561 {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        boolean flag = true;
        for(int i:nums){
            if(flag){
                sum += i;
            }
            flag ^= true;
        }
        return sum;
    }
}
