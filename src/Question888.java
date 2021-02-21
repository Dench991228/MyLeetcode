import java.util.HashSet;

public class Question888 {
    // sum_a-x+y=sum_b+x-y
    // x-(sum_a-sum_b)/2 = y
    public int[] fairCandySwap(int[] A, int[] B) {
        HashSet<Integer> number_a = new HashSet<>();
        HashSet<Integer> number_b = new HashSet<>();
        int sum_a = 0, sum_b = 0;
        for(int num:A){
            number_a.add(num);
            sum_a += num;
        }
        for(int num:B){
            number_b.add(num);
            sum_b += num;
        }
        for(int num:number_a){
            if(number_b.contains(num-(sum_a-sum_b)/2)){
                int[] result = new int[2];
                result[0] = num;
                result[1] = num-(sum_a-sum_b)/2;
                return result;
            }
        }
        return null;
    }
}
