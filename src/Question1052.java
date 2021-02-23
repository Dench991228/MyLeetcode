public class Question1052 {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int sum = 0;
        for(int i = 0;i < customers.length;i++){
            sum += grumpy[i]==0?customers[i]:0;
        }
        int saved = 0;//当前窗口被拯救的顾客
        int max_saved = 0;
        int left = 0, right = 0;
        while(right<X){
            saved += grumpy[right]==1?customers[right]:0;
            right++;
            max_saved = Math.max(max_saved, saved);
        }
        while(right<customers.length){
            saved -= grumpy[left]==0?0:customers[left];
            left++;
            saved += grumpy[right]==1?customers[right]:0;
            right++;
            max_saved = Math.max(max_saved, saved);
        }
        return sum + max_saved;
    }
}
