public class Question338 {
    /**
     * 计算1~num中所有数字的二进制表示中有多少1
     * */
    public int[] countBits(int num) {
        int[] number_one = new int[256];//统计0~255各个数有多少个1
        for(int i=0;i<256;i++){
            for(int j=0;j<8;j++){
                if((i&(1<<j))!=0){
                    number_one[i]++;
                }
            }
        }
        int[] result = new int[num+1];
        for(int i=0; i<=num; i++){
            int current_one = 0;
            int temp = i;
            while(temp!=0){
                current_one+=number_one[temp%256];
                temp/=256;
            }
            result[i] = current_one;
        }
        return result;
    }
}
