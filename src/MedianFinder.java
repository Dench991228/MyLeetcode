import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {

    /*big endian heap, containing numbers smaller than median*/
    private PriorityQueue<Integer> left_heap;
    /*little endian heap, containing numbers bigger equal than median*/
    private PriorityQueue<Integer> right_heap;
    /*median number of all*/
    private double median;
    /** initialize your data structure here. */
    public MedianFinder() {
        this.left_heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        this.right_heap = new PriorityQueue<>();
    }

    /**
     * add a number into the object
     * @param num new number to be added
     * */
    public void addNum(int num) {
        if(left_heap.size()==0&&right_heap.size()==0){// both heap are empty, add to right_heap
            this.right_heap.add(num);
            this.median = num;
        }else if(left_heap.size() == right_heap.size()){// both heap has same number of elements
            if(num>=median){
                right_heap.add(num);
            }else{
                left_heap.add(num);
                right_heap.add(left_heap.poll());
            }
            median = right_heap.peek();
        }else{// both heap has different number of elements
            if(num<median){
                left_heap.add(num);
            }else{
                right_heap.add(num);
                left_heap.add(right_heap.poll());
            }
            median = (1.0*left_heap.peek()+right_heap.peek())/2;
        }
        //System.out.println(this);
    }

    public double findMedian() {
        return this.median;
    }
    public String toString(){
        return this.left_heap.toString()+this.right_heap.toString();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */