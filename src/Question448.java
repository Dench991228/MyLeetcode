import java.util.LinkedList;
import java.util.List;

public class Question448 {
    private void printArr(int[] arr){
        for(int i:arr){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    /**
     * nums中理论上应该就是1~n，但是有些数字没有出现，所以这个函数的意义是不断更换i位置上的数字，直到nums[i]=i或者nums[nums[i]]=nums[i]
     * */
    private void move(int[] nums, int i){
        while(!(nums[i]==i+1||nums[nums[i]-1]==nums[i])){
            printArr(nums);
            int temp = nums[nums[i]-1];
            nums[nums[i]-1] = nums[i];
            nums[i] = temp;
        }
    }
    /**
     * 如果n为nums的长度，那么nums中有一些1-n中的数字没有出现，现在的任务是找出这些数字
     * @param nums 目标数组
     * */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int len = nums.length;
        LinkedList<Integer> result = new LinkedList<>();
        for(int i = 0;i<len;i++){
            move(nums, i);
            if(nums[i]!=i+1)result.addLast(i);
        }
        return result;
    }
}
