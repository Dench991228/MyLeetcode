import java.util.LinkedList;
import java.util.List;

public class Question119 {
    /**
     * 占用常数空间的杨辉三角形问题
     * */
    public List<Integer> getRow(int rowIndex) {
        int[] triangle = new int[rowIndex+1];
        for(int i=0;i<=rowIndex;i++){
            triangle[0] = 1;
            triangle[rowIndex] = 1;
            for(int j=rowIndex-1;j-1>=0;j--){
                System.out.println(j);
                triangle[j] = triangle[j] + triangle[j-1];
            }
        }
        LinkedList<Integer> result = new LinkedList<>();
        for(int i:triangle){
            result.addLast(i);
        }
        return result;
    }
}
