public class Question11 {
    public int maxArea(int[] height) {
        int leftBoundary = 0, rightBoundary = height.length -1;
        int current_containment = 0;
        int max_containment = 0;
        while(leftBoundary<rightBoundary){
            current_containment = Math.min(height[leftBoundary], height[rightBoundary])* (rightBoundary-leftBoundary);
            max_containment = Math.max(max_containment, current_containment);
            if(height[leftBoundary]<height[rightBoundary]){
                leftBoundary ++;
            }else{
                rightBoundary --;
            }
        }
        return max_containment;
    }
}
