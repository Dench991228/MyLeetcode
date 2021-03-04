import java.util.*;

public class Question354 {
    private boolean latterBigger(int[] o1, int[] o2){
        return o2[0] > o1[0] && o2[1] > o1[1];
    }
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return Integer.compare(o1[0], o2[0]);
                }else{
                    return Integer.compare(o1[1], o2[1]);
                }
            }
        });
        for(int[] e: envelopes){
            System.out.println(e[0]+" "+e[1]);
        }
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        int len = envelopes.length;
        int max_value = 0;
        for(int i=1;i<len;i++){
            for(int j=0;j<i;j++){
                if(latterBigger(envelopes[j], envelopes[i])){
                    dp[i] = Math.max(dp[j]+1, dp[i]);
                }
            }
            max_value = Math.max(max_value, dp[i]);
        }
        return max_value;
    }
}