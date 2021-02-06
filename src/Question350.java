import java.util.ArrayList;
import java.util.HashMap;

public class Question350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        int len;
        int i;
        HashMap<Integer,Integer> existed = new HashMap<Integer,Integer>();//用来存放已经出现过的数，以及出现次数
        ArrayList<Integer> result = new ArrayList<>();//用来存放结果，最后整理成数组输出
        if(nums1.length>nums2.length){//第一个数组比第二个大
            len = nums2.length;
            for(i=0;i<len;i++){
                if(existed.containsKey(nums2[i])){
                    existed.replace(nums2[i],existed.get(nums2[i])+1);
                }
                else{
                    existed.put(nums2[i],1);
                }
            }
            len = nums1.length;
            for(i=0;i<len;i++){
                if(existed.containsKey(nums1[i])){
                    int temp = existed.get(nums1[i]);
                    existed.replace(nums1[i],existed.get(nums1[i])-1);
                    result.add(nums1[i]);
                    if(temp == 1){
                        existed.remove(nums1[i]);
                    }
                }
            }
        }
        else{//第二个数组比第一个大
            len = nums1.length;
            for(i=0;i<len;i++){
                if(existed.containsKey(nums1[i])){
                    existed.replace(nums1[i],existed.get(nums1[i])+1);
                }
                else{
                    existed.put(nums1[i],1);
                }
            }
            len = nums2.length;
            for(i=0;i<len;i++){
                if(existed.containsKey(nums2[i])){
                    int temp = existed.get(nums2[i]);
                    existed.replace(nums2[i],existed.get(nums2[i])-1);
                    result.add(nums2[i]);
                    if(temp == 1){
                        existed.remove(nums2[i]);
                    }
                }
            }
        }
        len = result.size();
        int[] output = new int[len];
        for(i=0;i<len;i++){
            output[i] = result.get(i);
        }
        return output;
    }
}
