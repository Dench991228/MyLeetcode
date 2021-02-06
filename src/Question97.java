import java.util.ArrayList;

public class Question97 {
    // num_1是匹配过的数量，num_2是s2匹配过得数量
    private boolean dfs(String s1, String s2, String s3, int num_1, int num_2){
        if(num_1+num_2==s3.length())return true;
        if(num_1<s1.length()&&s1.charAt(num_1)==s3.charAt(num_1+num_2)&&dfs(s1,s2,s3,num_1+1,num_2)){//第一个能匹配上
            return true;
        }
        if(num_2<s2.length()&&s2.charAt(num_2)==s3.charAt(num_1+num_2)&&dfs(s1,s2,s3,num_1,num_2+1)){
            return true;
        }
        return false;
    }

    // 判断s1前i个和s2的前j个能不能与s3的前i+j个匹配
    private void get_dp(String s1, String s2, String s3, int i, int j, ArrayList<ArrayList<Boolean>> dp){
        //最后一个匹配的来自s2
        if(s2.charAt(j)==s3.charAt(i+j)){
            if(j==0)dp.get(i).set(j,Boolean.TRUE);
            else{
                if(dp.get(i).get(j-1)==Boolean.TRUE){
                    dp.get(i).set(j,Boolean.TRUE);
                }
                else{
                    dp.get(i).set(j,Boolean.FALSE);
                }
            }
        }
        else if(s1.charAt(i)==s3.charAt(i+j)){
            if(i==0)dp.get(i).set(j,Boolean.TRUE);
            else{
                if(dp.get(i-1).get(j)==Boolean.TRUE){
                    dp.get(i).set(j,Boolean.TRUE);
                }
                else{
                    dp.get(i).set(j,Boolean.FALSE);
                }
            }
        }
        else{
            dp.get(i).set(j,Boolean.FALSE);
        }
    }
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length())return false;
        return dfs(s1,s2,s3,0,0);
    }
}
