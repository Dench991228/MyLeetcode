public class Question978 {
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        int left=0, right=0;
        int result = 1;
        while(right<len-1){
            if(left==right){
                if(arr[left]==arr[left+1])left++;
                right++;
            }else{
                if(arr[right-1]<arr[right]&&arr[right+1]<arr[right]){
                    right++;
                }else if(arr[right-1]>arr[right]&&arr[right]<arr[right+1]){
                    right++;
                }else{
                    left=right;
                }
            }
            result = Math.max(result, right-left+1);
        }
        return result;
    }
}
