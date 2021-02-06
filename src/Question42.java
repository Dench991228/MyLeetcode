import java.util.LinkedList;

public class Question42 {
    /**
     * 维护一个单调递减栈，最后得到能够承接的雨水的数量
     * @param height 各个柱子的高度
     * @return 总共能装多少水
     */
    public int trap(int[] height) {
        int totalVolume = 0;
        int i, num_pillars = height.length;
        LinkedList<Integer> descend_stack = new LinkedList<>();
        for(i=0;i<num_pillars;i++){
            while(!descend_stack.isEmpty()&&height[descend_stack.peekLast()]<height[i]){
                int temp = descend_stack.pollLast();//最后一根柱子在哪
                System.out.println("pillar "+temp+" is popped!");
                if(!descend_stack.isEmpty()){//如果后面还有柱子，那就可以装下水
                    int leftEnd = descend_stack.peekLast();
                    int tempSum = (Math.min(height[i],height[descend_stack.peekLast()])-height[temp])*(i-leftEnd+1);
                    totalVolume += tempSum;
                    System.out.println("right height:"+height[i]);
                    System.out.println("left height:"+height[descend_stack.peekLast()]);
                    System.out.println("base height"+height[temp]);
                    System.out.println("width:"+(i-leftEnd+1));
                    System.out.println("temp volume:"+tempSum);
                }
            }
            descend_stack.addLast(i);
        }
        return totalVolume;
    }
}
