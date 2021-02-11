import java.util.PriorityQueue;

public class KthLargest {

    PriorityQueue<Integer> pq;//小顶堆，保存前K大的元素
    int K;

    public KthLargest(int k, int[] nums) {
        pq = new PriorityQueue<>();
        for(int n:nums){
            pq.add(n);
            if(pq.size()>k){
                pq.poll();
            }
        }
        this.K = k;
    }

    public int add(int val) {
        this.pq.add(val);
        if(pq.size()>K){
            pq.poll();
        }
        return pq.peek();
    }
}
