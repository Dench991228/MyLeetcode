class NumArray {
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

    /*线段树的根节点*/
    IntervalNode Root = null;
    int[] Numbers;

    private void buildTree(IntervalNode root, int[] nums){
        if(root.LowerBound==root.UpperBound)root.Sum = nums[root.LowerBound];
        else{
            int middle = (root.LowerBound+root.UpperBound)/2;
            IntervalNode left = new IntervalNode(root.LowerBound, middle);
            IntervalNode right = new IntervalNode(middle+1, root.UpperBound);
            root.LeftChild = left;
            root.RightChild = right;
            buildTree(left, nums);
            buildTree(right,nums);
            root.Sum = left.Sum+right.Sum;
        }
    }

    private void updateSubtree(IntervalNode root, int i, int val){
        Root.Sum += (val-Numbers[i]);
        if(root.UpperBound==Root.LowerBound)return;
        if(i<=root.LeftChild.UpperBound)updateSubtree(root.LeftChild, i, val);
        else updateSubtree(root.RightChild, i, val);
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

    public NumArray(int[] nums) {
        this.Numbers = nums;
        Root = new IntervalNode(0, nums.length-1);
        buildTree(Root, nums);
    }

    public void update(int i, int val) {
        Numbers[i] = val;
        updateSubtree(Root, i, val);
    }

    public int sumRange(int i, int j) {
        return sumSubtree(Root, i, j);
    }
}

public class Question307 {
}
