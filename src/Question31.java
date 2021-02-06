class Solution {
    /* 内排序剩下的部分 */
    private void innerSortDecsendingArray(int[] nums, int startPos){
        int len = nums.length;
        int i;
        for(i=0;i+startPos<len-1-i;i++){
            swap(nums, startPos+i, len-1-i);
        }
    }
    /* 交换数组中的两个数 */
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    /*从尾到头找出一个数组末尾升序列的开头下标 */
    private int getLongestAscendingArray(int[] nums){
        int len = nums.length;
        int i = len-1;
        int cur = Integer.MIN_VALUE;
        while(i>=0 && nums[i]>cur){
            cur = nums[i];
            i--;
        }
        return i+1;
    }
    /*
    0. 数组分为排序域和未排序域
    1. 从末尾开始，找出最长的升序列
    2. 如果整个数组都是升序列（从后往前），反转
    3. 否则，交换最长的升序列最后一位，和最长升序列前一位
    4. 从小到大排序剩下的部分（头尾交换即可）
    */
    public void nextPermutation(int[] nums) {
        int startPos = getLongestAscendingArray(nums);
        if(startPos==0){// 整个数组都是升序的（从尾到头），倒序
            innerSortDecsendingArray(nums, startPos);
        }
        else{// 整个数组部分升序（从尾到头），交换最后一位和第一位之前的，排序
            swap(nums, startPos-1, nums.length-1);
            innerSortDecsendingArray(nums, startPos);
        }
    }
}