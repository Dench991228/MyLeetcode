public class Question1130 {
    /**
     * 把[lower_bound, upper_bound)中的数拿出来，建立二叉树，看看最大值是多少
     * */
    private int dp(int[] arr, int lower_bound, int upper_bound){
        if(lower_bound==upper_bound-1)return arr[lower_bound];
        else if(lower_bound==upper_bound-2)return arr[lower_bound]*arr[lower_bound+1];
        else{
            int max_value = 0;
            for(int i=lower_bound+1;i<upper_bound;i++){
                max_value = Math.max(max_value, dp(arr, lower_bound, i)*dp(arr, i, upper_bound));
            }
            return max_value;
        }
    }
    public int mctFromLeafValues(int[] arr) {

    }
}
