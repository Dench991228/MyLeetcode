import java.util.ArrayList;

public class Question456 {
    public boolean find132pattern(int[] nums) {
        int len = nums.length;
        if(len<3)return false;
        ArrayList<Integer> isMin = new ArrayList<>();//记录一下每一个数之前的最小值（包括自己）
        int mini = nums[0];//到目前为止的最小的数
        isMin.add(nums[0]);
        int i;
        for(i=1;i<len;i++){
            if(nums[i]<mini){
                mini = nums[i];
            }
            isMin.add(mini);
            int j;
            for(j=0;j<i;j++){
                if(nums[i] < nums[j] && nums[j] > isMin.get(j)){
                    return true;
                }
            }
        }
        return false;
    }
}
