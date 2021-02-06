public class Question41 {
    public void swap(int[] src, int i, int j){
        int temp=src[i];
        src[i]=j;
        src[j]=temp;
    }
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        int i;
        for(i=0;i<len;i++){
            while(nums[i]-1>=0&&nums[i]-1<len&&nums[nums[i]-1]!=nums[i]){//可以哈希
                swap(nums,i,nums[i]-1);
            }
        }
        for(i=0;i<len;i++){
            if(nums[i]!=i+1)return i+1;
        }
        return len+1;
    }
}
