import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Question120 {
    //第一个参数是倒序的链表，第二个是正序的
    private void modifyDist(ArrayList<Integer> dist, List<Integer> cost){
        int len = cost.size();
        dist.add(dist.get(len-2)+cost.get(len-1));
        int i;
        for(i=len-2;i>=1;i--){
            dist.set(i,Math.min(dist.get(i),dist.get(i-1))+cost.get(i));
        }
        dist.set(0,dist.get(0)+cost.get(0));
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size() == 1){
            return triangle.get(0).get(0);
        }
        else {
            ArrayList<Integer> dist = new ArrayList<>();
            dist.add(triangle.get(0).get(0));
            int i, len = triangle.size();
            for (i = 1; i < len; i++) {
                this.modifyDist(dist, triangle.get(i));
            }
            len = dist.size();
            int minimum = Integer.MAX_VALUE;
            for (i = 0; i < len; i++) {
                minimum = Math.min(minimum, dist.get(i));
            }
            return minimum;
        }
    }
}
