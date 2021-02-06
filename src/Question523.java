import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Question523 {
    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer,Integer> has_mod = new HashMap<>();//目前已经存在的前缀和余数
        boolean has_zero=false;//包含0
        int i, len = nums.length;
        int sum = 0;
        has_mod.put(0,-1);
        if(k!=0){
            for(i=0;i<len;i++){
                sum+=nums[i];
                if(has_mod.containsKey(sum%k)&&has_mod.get(sum%k)!=i-1){
                    return true;
                }
                has_mod.put(sum%k,i);
            }
            return false;
        }
        else{
            for(i=0;i<len;i++){
                if(nums[i]==0&&has_zero){
                    return true;
                }
                else if(nums[i]==0){
                    has_zero=true;
                }
                else{
                    has_zero=false;
                }
            }
            return false;
        }
    }
}
