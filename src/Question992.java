import java.util.HashMap;

public class Question992 {
    private int subArrayMostK(int[] array, int k){
        int[] frequent = new int[array.length+1];
        int left = 0, right = 0, len = array.length;
        int current_diff = 0;
        int result = 0;
        while(right<len){
            if(frequent[array[right]]==0)current_diff++;
            frequent[array[right]]++;
            right++;
            while(current_diff>k){
                frequent[array[left]]--;
                if(frequent[array[left]]==0){
                    current_diff--;
                }
                left++;
            }
            result+=(right-left);
        }
        return result;
    }
    /**
     * 不断地寻找[left,right)使其中有不超过K个不同的数
     * */
    public int subarraysWithKDistinct(int[] A, int K) {
        return subArrayMostK(A, K)-subArrayMostK(A,K-1);
    }
}
