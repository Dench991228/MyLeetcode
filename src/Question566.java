public class Question566 {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int height = nums.length;
        int width = nums[0].length;
        if(r*c!=height*width)return nums;
        int[][] result = new int[r][c];
        int current= 0;
        for(int[] row:nums){
            for(int i:row){
                result[current/c][current%c] = i;
                current++;
            }
        }
        return result;
    }
}
