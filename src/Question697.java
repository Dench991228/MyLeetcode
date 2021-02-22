import java.util.HashMap;

public class Question697 {
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, Integer> first_see = new HashMap<>();
        HashMap<Integer, Integer> last_see = new HashMap<>();
        HashMap<Integer, Integer> frequency = new HashMap<>();
        for(int i=0;i<nums.length;i++){//统计所有元素的起始位置和始末
            if(!frequency.containsKey(nums[i])){
                frequency.put(nums[i],0);
            }
            frequency.put(nums[i], frequency.get(nums[i])+1);
            if(!first_see.containsKey(nums[i])){
                first_see.put(nums[i],i);
            }
            last_see.put(nums[i], i);
        }
        int max_appearance = 0;
        int shortest = Integer.MAX_VALUE;
        for(int num: frequency.keySet()){
            if(frequency.get(num)>max_appearance){
                shortest = last_see.get(num)-first_see.get(num)+1;
                max_appearance = frequency.get(num);
            }else if(frequency.get(num)==max_appearance){
                shortest = Math.min(last_see.get(num)-first_see.get(num)+1, shortest);
            }
        }
        return shortest;
    }
}
