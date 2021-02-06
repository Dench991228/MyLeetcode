import java.util.ArrayList;

public class Question96 {
    //nums是一共有i个数的时候，有多少种二叉搜索树可能性
    //n是当前有多少个数
    private void get_dp(ArrayList<Integer> nums, int n){
        int mid;//中间那个数是哪个
        int sum = 0;
        for(mid=1;mid<=n;mid++){
            sum += nums.get(mid-1)*nums.get(n-mid);
        }
        nums.add(sum);
    }
    public int numTrees(int n) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        int i;
        if(n==1)return 1;
        nums.add(1);
        nums.add(1);
        for(i=2;i<=n;i++){
            this.get_dp(nums,i);
        }
        return nums.get(n);
    }
}
