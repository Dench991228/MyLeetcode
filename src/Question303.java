class NumArray {
    private final int[] prefix_sum;

    public NumArray(int[] nums) {
        int len = nums.length;
        prefix_sum = new int[len];
        prefix_sum[0] = nums[0];
        for(int i=1;i<len;i++){
            prefix_sum[i] = prefix_sum[i-1]+nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return this.prefix_sum[j] - i==0?0:prefix_sum[i-1];
    }
}
