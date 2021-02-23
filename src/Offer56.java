public class Offer56 {
    private int lowBit(int x){
        return x&(-x);
    }
    public int[] singleNumbers(int[] nums) {
        int xor_all = 0;//所有数的异或
        for(int num:nums){
            xor_all^=num;
        }
        int class_one = 0, class_two = 0;
        int indicator = lowBit(xor_all);
        for(int num:nums){
            if((indicator&num)!=0){
                class_one^=num;
            }else{
                class_two^=num;
            }
        }
        int[] result = new int[2];
        result[0] = class_one;
        result[1] = class_two;
        return result;
    }
}
