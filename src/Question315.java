import java.util.LinkedList;
import java.util.List;

public class Question315 {
    /*输出nums中的每一个数右边有多少数比他小*/
    /*-10^4<=nums[i]<=10^4*/
    /*使用传统的线段树解决问题*/
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

    private void put(IntervalNode root, int number){
        root.Sum += 1;
        if(root.UpperBound-1!=root.LowerBound){
            if(number<root.LeftChild.UpperBound){
                put(root.LeftChild, number);
            }else{
                put(root.RightChild, number);
            }
        }
    }

    private int sumSubtree(IntervalNode root, int i, int j){
        if(root.LowerBound==i&&root.UpperBound==j){
            return root.Sum;
        }else{
            if(i<=root.LeftChild.UpperBound){//需要考虑左边
                if(j<=root.LeftChild.UpperBound)return sumSubtree(root.LeftChild, i, j);
                else{
                    return sumSubtree(root.LeftChild, i, root.LeftChild.UpperBound)+sumSubtree(root.RightChild, root.RightChild.LowerBound,j);
                }
            }else{
                return sumSubtree(root.RightChild, i, j);
            }
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        LinkedList<Integer> result = new LinkedList<>();
        int i, len = nums.length;
        IntervalNode root = new IntervalNode(-10000, 10001);
        buildTree(root);
        for(i=len-1;i>=0;i--){
            int count = sumSubtree(root, nums[i]-1, -10000);
            result.addFirst(count);
            put(root, nums[i]);
        }
        return result;
    }
}
