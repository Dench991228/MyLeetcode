import java.util.ArrayList;

class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int i,len=nums.length;
        ArrayList<Integer> odds=new ArrayList<Integer>();
        for(i=0;i<len;i++){
            if(nums[i]%2==1)odds.add(i+1);
        }
        if(odds.size()<k)return 0;
        else{
            int former, next;
            int j=k-1;
            i=0;
            int num=odds.size();
            int sum=0;
            while(j<num){
                former=i==0?0:odds.get(i-1);
                next=j!=num-1?odds.get(j+1):len;
                int a,b;
                if(odds.get(i)-former==0||odds.get(i)-former==1)a=1;
                else a=odds.get(i)-former;
                if(next-odds.get(j)==0||next-odds.get(j)==1)b=1;
                else b=next-odds.get(j);
                sum+=a*b;
                i++;
                j++;
            }
            return sum;
        }
    }
}
public class Question1248 {
}
