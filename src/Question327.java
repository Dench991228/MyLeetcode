import java.util.HashMap;
import java.util.TreeSet;

public class Question327 {
    /*使用传统的线段树的办法完成*/
    class Node{
        int Value;//值，如果LeftBound = RightBound
        int LeftBound;//区间左边界
        int RightBound;//区间又边界
        Node LeftChild;
        Node RightChild;

        public Node(){
            this.LeftChild = null;
            this.RightChild = null;
            this.Value = 0;
        }

        public Node(int left_bound, int right_bound){
            this.LeftBound = left_bound;
            this.RightBound = right_bound;
            this.LeftChild = null;
            this.RightChild = null;
            this.Value = 0;
        }
    }

    /**
     * 建造一棵树，根据父节点的左右边界，再其左右边创建节点，并且自动统计其和
     * @param father 父节点
     * @return 新建的两个子节点的Value之和
     * */
    private void buildTree(Node father){
        if(father.LeftBound==father.RightBound)return;
        int middle = (father.LeftBound + father.RightBound)/2;
        Node left = new Node(father.LeftBound, middle);
        Node right = new Node(middle+1, father.RightBound);

        father.LeftChild = left;
        if(left.LeftBound==left.RightBound){//左子节点两边相等，那就到底了
            left.Value = 0;
        }else{
            buildTree(left);
        }

        father.RightChild = right;
        if(right.LeftBound == right.RightBound){
            right.Value = 0;
        }else {
            buildTree(right);
        }
        father.Value = 0;
    }

    /**
     * 更新一棵树，输入一个区间（这道题是一个单独的数），使得包含这个数的区间的Value+1
     * @param root 线段树树根
     * @param new_value 插入的新值
     * */
    private void update(Node root, int new_value){
        root.Value += 1;
        if(root.LeftBound==root.RightBound)return ;

        if(new_value<=root.LeftChild.RightBound)update(root.LeftChild, new_value);
        else update(root.RightChild, new_value);
    }

    /**
     * 查找一个区间内有多少数
     * @param root 子树的树根
     * @param lower_bound 区间下限
     * @param upper_bound 区间上限
     * */
    private int search(Node root, int lower_bound, int upper_bound){
        if(lower_bound==root.LeftBound&&upper_bound==root.RightBound)return root.Value;
        int sum = 0;
        if(lower_bound<=root.LeftChild.RightBound){//需要在左边搜索
            if(upper_bound<root.LeftChild.RightBound){//完全从左边搜索
                sum += search(root.LeftChild, lower_bound, upper_bound);
            } else{//部分从左边搜索
                sum += search(root.LeftChild, lower_bound, root.LeftChild.RightBound);
                sum += search(root.RightChild, root.RightChild.LeftBound, upper_bound);
            }
        }else{
            sum += search(root.RightChild, lower_bound, upper_bound);
        }
        return sum;
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums==null||nums.length==0)return 0;
        int length = nums.length;
        long[] preSum = new long[length+1];
        long sum = 0;
        for(int i=0;i<length;i++){//记录preSum
            sum += nums[i];
            preSum[i+1] = sum;
        }
        TreeSet<Long> numbers = new TreeSet<>();//把可能的整数都计算进来
        for(long x: preSum){
            numbers.add(x);
            numbers.add(x-lower);
            numbers.add(x-upper);
        }
        System.out.println(numbers.toString());
        HashMap<Long, Integer> number_maps = new HashMap<>();
        int current_number = 0;
        for(long x:numbers){
            number_maps.put(x, current_number);
            current_number += 1;
        }
        System.out.println(number_maps.toString());
        Node root = new Node();
        root.LeftBound = 0;
        root.RightBound = number_maps.size()-1;
        root.Value = 0;
        buildTree(root);
        int output = 0;
        for(int i=0; i<length+1;i++){
            System.out.println("("+number_maps.get(preSum[i]-upper)+","+number_maps.get(preSum[i]-lower)+")");
            output += search(root, number_maps.get(preSum[i]-upper), number_maps.get(preSum[i]-lower));
            update(root, number_maps.get(preSum[i]));
        }
        return output;
    }
}
