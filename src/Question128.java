import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Question128 {
    /**
     * 基于哈希表获取一个数所在的集合
     * @param parent 并查集信息
     * @param target 目标数
     * @return 所在的集合编号
     */
    private int getSet(HashMap<Integer,Integer> parent, int target){
        if(parent.get(target)!=target){
            parent.put(target,this.getSet(parent,parent.get(target)));
        }
        return parent.get(target);
    }

    /**
     * 合并a和b两个元素
     * @param parent 记录并查集信息
     * @param sizes 记录集合大小信息
     * @param a 元素1
     * @param b 元素2
     */
    private void mergeSet(HashMap<Integer,Integer> parent, HashMap<Integer,Integer> sizes, int a, int b){
        int root_a = parent.get(a);
        int root_b = parent.get(b);
        sizes.put(root_a,sizes.get(root_a)+sizes.get(root_b));
        parent.put(root_b,root_a);
    }
    //最长公共子序列
    public int longestConsecutive(int[] nums) {
        int i, len = nums.length;
        HashMap<Integer,Integer> parent = new HashMap<>();
        HashMap<Integer,Integer> sizes = new HashMap<>();
        for(i=0;i<len;i++){
            if(!parent.containsKey(nums[i])){
                parent.put(nums[i],nums[i]);
                sizes.put(nums[i],1);
                if(parent.containsKey(nums[i]-1))mergeSet(parent,sizes,nums[i],nums[i]-1);
                if(parent.containsKey(nums[i]+1))mergeSet(parent,sizes,nums[i],nums[i]+1);
            }
        }
        int maxLength = 0;
        Set<Integer> sets = sizes.keySet();
        for(Integer num:sets){
            maxLength = Math.max(sizes.get(num),maxLength);
        }
        return maxLength;
    }
}
