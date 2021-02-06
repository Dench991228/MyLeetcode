public class Interview0803 {
    /**
     * 在左右两个下标之间，寻找魔术索引
     * @param nums 数组
     * @param leftBound 左边界
     * @param rightBound 右边界
     * @return 下标
     */
    private int tryFind(int[] nums, int leftBound, int rightBound){
        if(leftBound>rightBound)return -1;
        if(leftBound==rightBound){
            if(nums[leftBound]-leftBound==0)return leftBound;
            else return -1;
        }
        int mid = leftBound + (rightBound - leftBound) / 2;
        if(nums[mid]-mid==0)return mid;
        int leftFind = this.tryFind(nums,leftBound,mid-1);
        int rightFind = this.tryFind(nums,mid+1,rightBound);
        if(leftFind!=-1)return leftFind;
        else return rightFind;
    }
    public int findMagicIndex(int[] nums) {
        return this.tryFind(nums,0,nums.length-1);
    }
}
