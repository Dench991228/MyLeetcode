public class Question1423 {
    public int maxScore(int[] cardPoints, int k) {
        int i, len = cardPoints.length, j = len;
        /*计算[0,i)与[j, len)之间的和*/
        int current_sum = 0;
        for(i=0;i<k;i++){
            current_sum += cardPoints[i];
        }
        int max_sum = current_sum;
        while(i>0){
            current_sum += cardPoints[--j];
            current_sum -= cardPoints[--i];
            max_sum = Math.max(max_sum, current_sum);
        }
        return max_sum;
    }
}
