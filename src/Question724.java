public class Question724 {
    public int pivotIndex(int[] nums) {
        if(nums.length==0)return -1;
        if(nums.length==1)return 0;
        int[] prefix_sum = new int[nums.length];
        int length = nums.length;
        prefix_sum[0] = nums[0];
        for(int i=1;i<length;i++){
            prefix_sum[i] = prefix_sum[i-1] + nums[i];
        }
        for(int i=0;i<length;i++){
            if(prefix_sum[i]-nums[i]==prefix_sum[length-1]-prefix_sum[i]){
                return i;
            }
        }
        return -1;
    }
}
