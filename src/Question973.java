import java.util.PriorityQueue;
import java.math.*;
public class Question973 {
    public static void main(String[] args){
        System.out.println("Hello world!");
        PriorityQueue<Dot> pq = new PriorityQueue<>();
        Dot a = new Dot(1,1);
        Dot b = new Dot(2,2);
        Dot c = new Dot(1,2);
        pq.add(a);
        pq.add(b);
        pq.add(c);
        while(!pq.isEmpty()){
            Dot d = pq.poll();
            System.out.println(d.x+" "+d.y);
        }
    }
}
class Dot implements Comparable{
    int x;
    int y;
    public Dot(){

    }
    public Dot(int a, int b){
        this.x = a;
        this.y = b;
    }
    public double dist(){
        return Math.sqrt(x*x+y*y);
    }
    public int compareTo(Object o){
        Dot d = (Dot)o;
        if(this.dist()>d.dist()){
            return -1;
        }
        else{
            return 1;
        }
    }
}
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Dot> pq = new PriorityQueue<>();
        int len = points.length;
        for(int i=0;i<len;i++){
            Dot d = new Dot(points[i][0], points[i][1]);
            if(pq.size()==K&&pq.peek().dist()>d.dist()){/*如果pq已经有超过k个元素，那就比较一下再决定放不放进去 */
                pq.poll();
                pq.add(d);
            }
            else{
                pq.add(d);
            }
        }
        int[][] results = new int[K][2];
        int i=0;
        while(!pq.isEmpty()){
            Dot d = pq.poll();
            results[i][0] = d.x;
            results[i][1] = d.y;
            i++;
        }
        return results;
    }
}