import java.util.Comparator;
import java.util.PriorityQueue;

public class Question480 {
    class multiSet{
        PriorityQueue<Integer> smallEndian;//最大的一半
        PriorityQueue<Integer> bigEndian;//最小的一半
        public multiSet(){
            this.smallEndian = new PriorityQueue<>();
            this.bigEndian = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if(o2>o1)return 1;
                    else if(o2.equals(o1))return 0;
                    else return -1;
                }
            });
        }
        public double getMedian(){
            if(this.smallEndian.size()<this.bigEndian.size()){
                return 1.0*this.bigEndian.peek();
            }else{
                int a = this.smallEndian.size()==0?0:this.smallEndian.peek();
                int b = this.bigEndian.size()==0?0:this.bigEndian.peek();
                return (a*1.0+b)/2;
            }
        }
        public void add(int x){
            double median = this.getMedian();
            if(x>median){//放在后半部分
                this.smallEndian.add(x);
            }else{//放在前半部分
                this.bigEndian.add(x);
            }
            if(this.bigEndian.size()>this.smallEndian.size()+1){
                this.smallEndian.add(this.bigEndian.poll());
            }
            if(this.smallEndian.size()>this.bigEndian.size()){
                this.bigEndian.add(this.smallEndian.poll());
            }
        }
        public void remove(int x){
            if(x<=this.getMedian())this.bigEndian.remove(x);
            else this.smallEndian.remove(x);
            if(this.bigEndian.size()>this.smallEndian.size()+1){
                this.smallEndian.add(this.bigEndian.poll());
            }
        }
        public String toString(){
            return this.bigEndian.toString()+"\n"+this.smallEndian.toString();
        }
    }
    public double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        double[] result = new double[len-k+1];
        int left = 0, right = 0;
        multiSet set = new multiSet();
        while(right<k){
            set.add(nums[right++]);
        }
        while(right<len){
            result[left] = set.getMedian();
            set.remove(nums[left++]);
            set.remove(nums[right++]);
        }
        return result;
    }
}
