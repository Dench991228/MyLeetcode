public class Question35 {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        if(len == 0){//空数组，直接放进去
            return 0;
        }
        else if(target<=nums[0]){//比第一个元素小，不用二分，直接放进去
            return 0;
        }
        else if(target>nums[len-1]){//比最后一个元素大
            return len;
        }
        else{
            int upper_bound = len-1;
            int lower_bound = 0;
            int mid_value = lower_bound+(upper_bound-lower_bound)/2;
            while(upper_bound!=lower_bound){
                if(target>nums[mid_value]){
                    lower_bound = mid_value+1;
                }
                else{
                    upper_bound = mid_value;
                }
                mid_value = lower_bound+(upper_bound-lower_bound)/2;
            }
            return upper_bound;
        }
    }
}
