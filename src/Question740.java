import java.util.Vector;

public class Question740 {
    public int deleteAndEarn(int[] nums) {
        int[] count = new int[10001];
        for(int x:nums){
            count[x]++;
        }
        int not_counted_max = 0, counted_max = 0, prev_max = -1;
        int i;
        for(i=0;i<=10000;i++){
            if(count[i]>0){//如果这个数存在
                int m = counted_max > not_counted_max? counted_max:not_counted_max;
                if(prev_max == i-1){//相邻
                    counted_max = not_counted_max + i * count[i];
                }
                else{
                    counted_max = Math.max(counted_max,not_counted_max) + i * count[i];
                }
                not_counted_max = m;
                prev_max = i;
            }
        }
        return Math.max(counted_max,not_counted_max);
    }
}
