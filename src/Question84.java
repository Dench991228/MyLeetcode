import java.util.LinkedList;

public class Question84 {
    public int largestRectangleArea(int[] heights) {
        if(heights.length==0)return 0;
        LinkedList<Integer> stack = new LinkedList<>();
        LinkedList<Integer> left = new LinkedList<>();
        int max_area = 0;
        stack.addLast(0);
        left.addLast(0);
        int i;
        for(i=1;i<heights.length;i++){
            if(heights[i]<heights[stack.peekLast()]){//新入栈一个柱子
                while(!stack.isEmpty()&&heights[stack.peekLast()]>heights[i]){
                    int position = stack.pollLast();
                    int left_value = left.pollLast();
                    max_area = Math.max(max_area, (i-position)*heights[position]+left_value);
                }
            }
            int left_position = stack.isEmpty()?0:stack.peekLast();
            left.addLast((i-left_position)*heights[i]);
            stack.addLast(i);
        }
        while(!stack.isEmpty()){
            int position = stack.pollLast();
            int left_value = left.pollLast();
            max_area = Math.max(max_area, (i-position)*heights[position]+left_value);
        }
        return max_area;
    }
}
