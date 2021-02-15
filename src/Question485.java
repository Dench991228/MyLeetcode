public class Question485 {
    /**
     * 数组里面的数不是1就是0，找出最长的1的序列
     * */
    public int findMaxConsecutiveOnes(int[] nums) {
        int current = 0, maximum = 0;
        int left = 0, right = 0;
        while(left<nums.length){
            left=right;
            if(nums[left]!=1){
                left++;
            }
            right = left;
            while(right<nums.length&&nums[right]==1){
                current++;
                right++;
            }
            maximum = Math.max(current, maximum);
            current = 0;
        }
        return maximum;
    }
}
