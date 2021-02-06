import java.util.LinkedList;

public class Question1025 {
    // 计算每个数以爱丽丝或者鲍勃先出手，谁能赢，后面的数直接根据这个计算
    // 如果爱丽丝先出手可以赢，那么鲍勃先出手就可以赢
    // record[i+1]=(!record[i-m]&&(i+1)%m==0)||(!record[i-m-1]&&(i+1)%(m+1)==0)...)
    public boolean divisorGame(int N) {
        LinkedList<Integer> loses = new LinkedList<>(); //这些数，爱丽丝赢不了
        if(N==1)return false;
        loses.addFirst(1);
        int i;
        boolean res = false;
        for(i=2;i<=N;i++){
            res = false;
            for(Integer it:loses){ //去掉第一个数之后，如果剩下it，那么爱丽丝就能赢
                if(i%(i-it)==0){
                    res=true;
                    break;
                }
            }
            if(!res)loses.addFirst(i);//这个数爱丽丝赢不了
        }
        return res;
    }
}
