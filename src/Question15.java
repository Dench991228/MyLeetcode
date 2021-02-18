import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Question15 {
    /**
     * 在[start, nums.length)中间找出来两个数，使得他们的和是一个特定值
     * @param nums 数组，从小到大排序
     * @param start 开始位置的下标
     * @param target 目标值
     * @return 一个列表，每一项是两个数
     * */
    private List<int[]> getSumTwo(int[] nums, int start, int target){
        int s = start, e = nums.length-1;
        List<int[]> result = new LinkedList<>();
        while(s<e){
            if(nums[s]+nums[e]==target){//相等，添加这个数组，向右移动s指针
                int[] pair = new int[2];
                pair[0] = nums[s];
                pair[1] = nums[e];
                result.add(pair);
                int temp = nums[s];
                while(s<nums.length&&nums[s]==temp){
                    s++;
                }
            }else if(nums[s]+nums[e]<target){//较小，向右移动s指针
                s++;
            }else{//较大左移e指针
                e--;
            }
        }
        return result;
    }
    private void printArr(int[] nums){
        for(int i:nums){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        if(nums.length<3||nums[0]>0||nums[nums.length-1]<0)return result;
        int former = Integer.MIN_VALUE;
        for(int i = 0;i<nums.length;i++){
            List<int[]> pairs = getSumTwo(nums, i+1, -nums[i]);
            if(nums[i]!=former){
                for(int[] pair:pairs){
                    LinkedList<Integer> sums = new LinkedList<>();
                    sums.addLast(nums[i]);
                    sums.addLast(pair[0]);
                    sums.addLast(pair[1]);
                    result.add(sums);
                }
                former = nums[i];
            }
        }
        return result;
    }
}
