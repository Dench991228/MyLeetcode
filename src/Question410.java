public class Question410 {
    /**
     * 检查一个最大数组和能不能成立
     * @param nums 数组
     * @param max_sum 最大的子数组和
     * @param num_cut 最多的分割数量
     */
    private boolean test(int[] nums, int max_sum, int num_cut){
        int i = 0;
        int len = nums.length;
        int cur_sum = 0;
        int cur_cut = 0;
        while(i<len){
            while(i<len&&cur_sum<=max_sum){
                if(cur_sum+nums[i]<=max_sum){
                    cur_sum+=nums[i];
                    i++;
                }else{
                    cur_sum = 0;
                    break;
                }
            }
            cur_cut++;
        }
        return cur_cut <= num_cut;
    }
    public int splitArray(int[] nums, int m) {
        int i;
        int max = 0;
        for(i=0;i<nums.length;i++){
            max = Math.max(nums[i],max);
        }
        int leftBound = max, rightBound = Integer.MAX_VALUE;
        int mid = leftBound + (rightBound - leftBound) / 2;
        while(leftBound!=rightBound){
            //如果可以完全分出来
            System.out.println(mid);
            if(this.test(nums,mid,m)){
                rightBound = mid;
            }
            else{
                leftBound = mid + 1;
            }
            mid = leftBound + (rightBound - leftBound) / 2;
        }
        return rightBound;
    }
}
