public class Question717 {
    public boolean isOneBitCharacter(int[] bits) {
        int len = bits.length;
        if(len==1)return true;
        int i= len-2;
        if(i==0)return true;
        int num = 0;
        while(i>=0&&bits[i]!=0){
            num++;
            i--;
        }
        return num%2==0;
    }
}
