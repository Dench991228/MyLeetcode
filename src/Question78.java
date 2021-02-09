import java.util.LinkedList;
import java.util.List;

public class Question78 {
    /**
     * decode a number into binary and put the bits into an array
     * @param number the number to be decoded
     * @param array the array to store bits, its maximum length is 10
     * */
    private void generateBits(boolean[] array, int number){
        int i;
        for(i=0;i<10;i++){
            array[i] = (number&(1<<i))==0;
        }
    }
    /**
     * put elements in nums into a LinkedList, whether a element will be put into the list depends on contains
     * @param nums rt
     * @param contains rt
     * @return rt
     * */
    private LinkedList<Integer> generateList(int[] nums, boolean[] contains){
        LinkedList<Integer> result = new LinkedList<>();
        int i, len = nums.length;
        for(i=0;i<len;i++){
            if(contains[i]){
                result.add(nums[i]);
            }
        }
        return result;
    }
    public List<List<Integer>> subsets(int[] nums) {
        boolean[] contains = new boolean[10];
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        int max = 1<<nums.length;
        for(int i=0;i<max;i++){
            generateBits(contains, i);
            result.add(generateList(nums, contains));
        }
        return result;
    }
}
