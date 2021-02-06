import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Question699 {
    private class IntervalTreeNode{
        int Highest;
        int CachedHeight;//没有来得及向下传递的高度变化
        int UpperBound;
        int LowerBound;
        IntervalTreeNode LeftChild;
        IntervalTreeNode RightChild;
        IntervalTreeNode Parent;

        public IntervalTreeNode(){
            this.Highest = 0;
            this.CachedHeight = 0;
            this.LeftChild = null;
            this.RightChild = null;
            this.Parent = null;
        }

        public IntervalTreeNode(int lower_bound, int upper_bound){
            this.Highest = 0;
            this.CachedHeight = 0;
            this.LowerBound = lower_bound;
            this.UpperBound = upper_bound;
            this.LeftChild = null;
            this.RightChild = null;
            this.Parent = null;
        }

        public String toString(){
            return "("+LowerBound+","+UpperBound+","+Highest+","+CachedHeight+")";
        }

        public void print(){
            LinkedList<IntervalTreeNode> toBePrinted = new LinkedList<>();
            StringBuilder string = new StringBuilder();
            toBePrinted.addLast(this);
            string.append(this.toString());
            while(!toBePrinted.isEmpty()){
                IntervalTreeNode current = toBePrinted.pollFirst();
                if(current.LeftChild!=null){
                    toBePrinted.addLast(current.LeftChild);
                    string.append(current.LeftChild.toString());
                }else{
                    string.append("null");
                }

                if(current.RightChild!=null){
                    toBePrinted.addLast(current.RightChild);
                    string.append(current.RightChild.toString());
                }else{
                    string.append("null");
                }
            }
            System.out.println(string.toString());
        }
    }

    private void buildSubtree(IntervalTreeNode root){
        System.out.println(root);
        if(root.LowerBound!=root.UpperBound-1){
            int middle = (root.LowerBound+root.UpperBound)/2;
            IntervalTreeNode left = new IntervalTreeNode(root.LowerBound, middle);
            IntervalTreeNode right = new IntervalTreeNode(middle, root.UpperBound);
            root.LeftChild = left;
            root.RightChild = right;
            left.Parent = root;
            right.Parent = root;
            buildSubtree(left);
            buildSubtree(right);
        }
    }
    /**
     * 查找一个区间内的最大值
     * @param root 线段树的树根
     * @param lower_bound 查询区间的下界
     * @param upper_bound 查询区间的上界
     * @return 当前这个区间最高的方块的高度
     * */
    private int search(IntervalTreeNode root, int lower_bound, int upper_bound){
        if(lower_bound<=root.LowerBound&&upper_bound>=root.UpperBound){
            return root.Highest + root.CachedHeight;
        }else{//只是部分覆盖，那就麻烦了，需要继续往下走，并且需要把Cached传播下去，并且需要保存当前的高度
            /*向下传递缓存，使得左右的高度加上缓存等于现在节点的高度加缓存*/
            if(root.LeftChild!=null){
                root.LeftChild.CachedHeight += (root.Highest+root.CachedHeight)-(root.LeftChild.Highest+root.LeftChild.CachedHeight);
            }
            if(root.RightChild!=null){
                root.RightChild.CachedHeight += (root.Highest+root.CachedHeight)-(root.RightChild.Highest+root.RightChild.CachedHeight);
            }
            /*继续向下搜索，并且汇总两边的最大值*/
            int left_height = 0;
            int right_height = 0;
            if(root.LeftChild!=null&&lower_bound<root.LeftChild.UpperBound){//需要往左子树传播
                left_height = search(root.LeftChild, lower_bound, Math.min(root.LeftChild.UpperBound, upper_bound));
            }
            if(root.RightChild!=null&&upper_bound>root.RightChild.LowerBound){//需要往右子树传播
                right_height = search(root.RightChild, Math.max(lower_bound, root.RightChild.LowerBound), upper_bound);
            }
            /*因为缓存向下传递了，更新当前节点的高度*/
            root.CachedHeight = 0;
            root.Highest = Math.max(left_height, right_height);
            return root.Highest;
        }
    }
    /**
     * 把一个方块放到线段树中，把这个区间内所有的方块高度设置为height
     * @param root 线段树的树根
     * @param lower_bound 查询区间的下界
     * @param upper_bound 查询区间的上界
     * @param height 把这个区间的方块的高度都变成height
     * @return 当前这个区间最高的方块的高度
     * */
    private void put(IntervalTreeNode root, int lower_bound, int upper_bound, int height){
        if(lower_bound<=root.LowerBound&&upper_bound>=root.UpperBound){
            root.CachedHeight += (height-root.CachedHeight-root.Highest);//更新的区间包含当前区间，那就更新缓存，然后就不往下更新了，这个是盖住整个区间的
        }else{//只是部分覆盖，那就麻烦了，需要继续往下走，并且需要把Cached传播下去，并且需要保存当前的高度
            /*向下传递缓存，使得左右的高度加上缓存等于现在节点的高度加缓存*/
            if(root.LeftChild!=null){
                root.LeftChild.CachedHeight += (root.Highest+root.CachedHeight)-(root.LeftChild.Highest+root.LeftChild.CachedHeight);
            }
            if(root.RightChild!=null){
                root.RightChild.CachedHeight += (root.Highest+root.CachedHeight)-(root.RightChild.Highest+root.RightChild.CachedHeight);
            }
            /*继续向下传递缓存问题*/
            if(root.LeftChild!=null&&lower_bound<root.LeftChild.UpperBound){//需要往左子树传播
                put(root.LeftChild, lower_bound, Math.min(root.LeftChild.UpperBound, upper_bound), height);
            }
            if(root.RightChild!=null&&upper_bound>root.RightChild.LowerBound){//需要往右子树传播
                put(root.RightChild, Math.max(lower_bound, root.RightChild.LowerBound), upper_bound, height);
            }
            int left_height = root.LeftChild==null?0:root.LeftChild.Highest+root.LeftChild.CachedHeight;
            int right_height = root.RightChild==null?0:root.RightChild.Highest+root.RightChild.CachedHeight;
            /*因为缓存向下传递了，更新当前节点的高度*/
            root.CachedHeight = 0;
            root.Highest = Math.max(left_height, right_height);
        }
    }

    public List<Integer> fallingSquares(int[][] positions) {
        /*将可能的左右端点算出来，进行离散化*/
        TreeSet<Integer> numbers = new TreeSet<>();
        for(int[] square:positions){
            numbers.add(square[0]);
            numbers.add(square[0]+square[1]);
        }
        HashMap<Integer, Integer> number_maps = new HashMap<>();
        int id = 0;
        for(int i:numbers){
            number_maps.put(i, id);
            id++;
        }
        LinkedList<Integer> heights = new LinkedList<>();
        IntervalTreeNode root = new IntervalTreeNode(0, number_maps.size());
        buildSubtree(root);
        root.print();
        for(int[] square:positions){
            int left = number_maps.get(square[0]);
            int right = number_maps.get(square[0]+square[1]);
            System.out.println("put:("+left+","+right+","+square[1]+")");
            int new_height = search(root, left, right)+square[1];
            System.out.println("new height:"+new_height);
            root.print();
            put(root, number_maps.get(square[0]), number_maps.get(square[0]+square[1]), new_height);
            int result = root.CachedHeight+root.Highest;
            heights.addLast(result);
            root.print();
        }
        return heights;
    }
}
