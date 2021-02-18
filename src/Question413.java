import java.util.LinkedList;

public class Question413 {
    public int numberOfArithmeticSlices(int[] A) {
        LinkedList<Integer> slice = new LinkedList<>();
        int expected_diff = 0;
        int result = 0;
        for(int num:A){
            if(slice.size()==0){
                slice.addLast(num);
            }else if(slice.size()==1){
                expected_diff = num - slice.peekLast();
                slice.addLast(num);
            }else{
                if(num-slice.peekLast()!=expected_diff){
                    while(slice.size()!=1){
                        slice.pollFirst();
                    }
                    expected_diff = num - slice.peekLast();
                    slice.addLast(num);
                }else{
                    slice.addLast(num);
                    result += slice.size()-2;
                }
            }
        }
        return result;
    }
}
