import java.util.*;

public class Question632 {
    /**
     * 把包含某个元素的所有列表的编号记录到表中
     * @param target 记录表
     * @param id_lists 表的编号
     */
    private void addElement(Map<Integer,Integer> target, List<Integer> id_lists){
        for(int i:id_lists){
            if(target.containsKey(i)){
                target.replace(i,target.get(i)+1);
            }
            else{
                target.put(i,1);
            }
        }
    }

    /**
     * 从记录表中删除掉一个元素对应的列表
     * @param target 记录表
     * @param id_lists 元素对应的编号
     */
    private void eraseElement(Map<Integer,Integer> target, List<Integer> id_lists){
        for(Integer i:id_lists){
            if(target.containsKey(i)){
                if(target.get(i)>=2){
                    target.replace(i,target.get(i)-1);
                }
                else{
                    target.remove(i);
                }
            }
        }
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        /*先建表，按照值->包含此值的列表来进行构件*/
        TreeMap<Integer,List<Integer>> chart = new TreeMap<>();
        int i=0;
        /*初始化哈希表*/
        for(List<Integer> l:nums){
            for(Integer num:l){
                ArrayList<Integer> lists = (ArrayList<Integer>) chart.getOrDefault(num,new ArrayList<>());
                /*第i个列表里面有这个数*/
                lists.add(i);
            }
            i++;
        }
        /*当前窗口中包含哪些列表*/
        Map<Integer,Integer> containedList = new HashMap<>();
        /*所有列表中存在的数，在这上面滑动窗口*/
        Integer[] possibleNumbers = chart.keySet().toArray(new Integer[0]);
        /*总共有多少个不同的元素*/
        int num_elements = possibleNumbers.length;
        /*当前窗口的左边界，有边界*/
        int start=0,end=0;
        int leftBound = 0;
        int rightBound = 0;
        int min_width = Integer.MAX_VALUE;
        /*当前窗口内的全部列表数量*/
        int cur_size = containedList.size();
        while(end<num_elements){
            /*滑动窗口中的元素并不能覆盖全部的列表，那就加入新的元素*/
            while(end<num_elements&&cur_size<nums.size()){
                this.addElement(containedList,chart.get(possibleNumbers[end]));
                end++;
                cur_size = containedList.size();
            }
            if(rightBound-leftBound<min_width){
                leftBound = possibleNumbers[start];
                rightBound = possibleNumbers[end];
                min_width = rightBound - leftBound;
            }
            this.eraseElement(containedList,chart.get(possibleNumbers[start]));
            start++;
            cur_size = containedList.size();
        }
        return new int[]{leftBound,rightBound};
    }
}
