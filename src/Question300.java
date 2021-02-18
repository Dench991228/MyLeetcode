import java.util.ArrayList;
import java.util.Arrays;

public class Question300 {
    /**
     * 返回在数组nums中的[left, right)中第一个大于等于target的数，保证nums[]从小到大排列
     * @param nums 目标数组
     * @param left 左边界
     * @param right 右边界
     * @param target 目标数值
     * @return 第一个大于等于target的数的下标，如果都小于target，那就是right
     * */
    private int upperBound(int[] nums, int left, int right, int target){
        while(left<right){
            int mid = left+(right-left)/2;
            if(nums[mid]<=target){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return left;
    }
    /**
     * 打印一个数组
     * */
    private void printArr(int[] nums){
        for(int i:nums) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public int lengthOfLIS1(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 1;
        dp[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            int pos = upperBound(dp, 0, len, nums[i]);
            if(pos==0||(pos-1>=0&&dp[pos-1]!=nums[i])){
                dp[pos] = nums[i];
                if(pos==len)len++;
            }
            printArr(dp);
        }
        return len;
    }
    public int lengthOfLIS(int[] nums) {
        int[] length = new int[nums.length];//以每一个数为末尾，最长的递增子序列
        int len = nums.length;
        int max_length = 1;
        length[0] = 1;
        for(int i=1;i<len;i++){
            length[i] = 1;
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]){
                    length[i] = Math.max(length[j]+1, length[i]);
                    max_length = Math.max(max_length, length[i]);
                }
            }
        }
        return max_length;
    }
}
