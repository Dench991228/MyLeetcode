import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

public class Question493 {
    class IntervalNode{
        int Sum;//这个区间所有值的和
        int UpperBound;//上边界
        int LowerBound;//下边界
        IntervalNode LeftChild;//左子节点
        IntervalNode RightChild;//右子节点

        public IntervalNode(){
            Sum = 0;
        }

        public IntervalNode(int lower_bound, int upper_bound){
            this.Sum = 0;
            this.UpperBound = upper_bound;
            this.LowerBound = lower_bound;
            this.LeftChild = null;
            this.RightChild = null;
        }
    }

    /**
     * 递归的构建一棵树
     * @param root 树根
     * */
    private void buildTree(IntervalNode root){
        System.out.println("("+root.LowerBound+","+root.UpperBound+")");
        if (root.LowerBound != root.UpperBound) {
            int middle = (root.LowerBound + root.UpperBound) / 2;
            IntervalNode left = new IntervalNode(root.LowerBound, middle);
            IntervalNode right = new IntervalNode(middle, root.UpperBound);
            root.LeftChild = left;
            root.RightChild = right;
            buildTree(left);
            buildTree(right);
        }
        root.Sum = 0;
    }

    /**
     * 往线段树中放入一个数
     * @param root 线段树的树根
     * @param number 放入的数
     * */
    private void putNumber(IntervalNode root, int number){
        root.Sum += 1;
        if(root.LowerBound!=root.UpperBound-1){
            if(number<root.LeftChild.UpperBound){
                putNumber(root.LeftChild, number);
            }else{
                putNumber(root.RightChild, number);
            }
        }
    }

    /**
     * 计算一个区间内的数的数量
     * */
    private int count(IntervalNode root, int lower_bound, int upper_bound){
        LinkedList<IntervalNode> overlapped_intervals = new LinkedList<>();
        overlapped_intervals.addLast(root);
        int sum = 0;
        while(!overlapped_intervals.isEmpty()){
            IntervalNode current = overlapped_intervals.pollFirst();
            if(current.UpperBound<=upper_bound&&current.LowerBound>=lower_bound){//目标区间包含这个，不需要扩展了
                sum += current.Sum;
            }else{//需要继续向下扩展
                if(current.LeftChild!=null&&lower_bound<current.LeftChild.UpperBound){
                    overlapped_intervals.addLast(current.LeftChild);
                }
                if(current.RightChild!=null&&upper_bound>current.RightChild.LowerBound){
                    overlapped_intervals.addLast(current.RightChild);
                }
            }
        }
        return sum;
    }

    /**
     * 对于nums[i]>2*nums[j]且i<j，我们称其为重要翻转对，现在需要找出nums中的重要翻转对
     * @param nums 数组
     * @return 重要翻转对的数量
     * */
    public int reversePairs(int[] nums) {
        /*先把数字离散化一下*/
        TreeSet<Long> numbers = new TreeSet<>();
        for(long i:nums){
            numbers.add(i);
            numbers.add(i*2+1);
        }
        HashMap<Long, Integer> number_maps = new HashMap<>();
        int id = 0;
        for(long i:numbers){
            number_maps.put(i, id);
            id ++;
        }
        int num_reverse_pairs = 0;
        IntervalNode root = new IntervalNode(0, id);
        buildTree(root);
        /*遍历数组，每遇到一个数，我都先寻找现在树中比它的两倍大的数，然后把它放进去*/
        for(int i:nums){
            num_reverse_pairs += count(root, number_maps.get(((long)i)*2+1), id);
            putNumber(root, number_maps.get((long)(i)));
        }
        return num_reverse_pairs;
    }
}
