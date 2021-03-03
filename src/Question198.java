public class Question198 {
    public int rob(int[] nums) {
        if(nums.length==0)return 0;
        int[][] cash = new int[2][nums.length];//cash[0][i]表明访问了0~i号房子，没有枪i的情况，cash[1][i]以此类推
        cash[0][0] = 0;
        cash[1][0] = nums[0];
        for(int i=1;i<nums.length;i++){
            cash[0][i] = Math.max(cash[1][i-1], cash[0][i-1]) + nums[i];
            cash[1][i] = cash[0][i-1] + nums[i];
        }
        return Math.max(cash[1][nums.length], cash[0][nums.length]);
    }
}
