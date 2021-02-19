public class Question1004 {
    public int longestOnes(int[] A, int K) {
        int left = 0, right = 0;//双指针的左右两边
        int current_zero = 0;//[left, right)中有多少个零
        int max_length = 0;
        while(right<A.length){
            while(current_zero<=K&&right<A.length){//找出一个右边界，停下的时候，要么正好是第K+1个0，要么到边界了
                if(A[right]==0)current_zero++;
                right++;
            }
            if(right>=A.length&&current_zero>K){//两种可能并行
                right--;
                break;
            }else if(right>=A.length) {//超边界，0的个数没超
                break;
            }else{//达到要求
                current_zero--;
                right--;
            }
            max_length = Math.max(max_length, right - left);
            while(A[left]!=0){
                left++;
            }
            left++;
            current_zero--;
        }
        max_length = Math.max(max_length, right - left);
        return max_length;
    }
}
