public class Question665 {
    public boolean checkPossibility(int[] nums) {
        if(nums.length==1)return true;
        int max = Integer.MIN_VALUE;
        int position = -1;//出问题的地方
        boolean flag = false;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>=max){
                max = nums[i];
            }else{
                if(flag)return false;
                else{
                    flag = true;
                    position = i;
                    max = nums[i];
                }
            }
        }
        //System.out.println("position:"+position);
        /*尝试修复出问题的地方*/
        if(position==-1)return true;
        if(position==1||position==nums.length-1)return true;
        else if(nums[position+1]>=nums[position-1]){//把position处的数变大成后面的数
            return true;
        }else if(nums[position]>=nums[position-2]){
            return true;
        }else{
            return false;
        }
    }
}
