import java.util.ArrayList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/*
* [71,67,87,74,39,23,38,null,16,62,65,61,80,46,37,46,87,10,12,9,51,null,92,33,43,67,24,1,67,null,null,null,null,null,null,null,70,null,1,69,50,null,26,null,14,89,9,94,65,64,96,81,29,20,87,null,25,56,66,null,59,68,45,null,null,null,23,null,29,63,86,null,null,null,94,null,67,74,81,null,19,87,45,67,8,13,13,null,48,null,null,38,85,null,77,null,63,45,94,null,69,null,92,null,88,82,21,91,null,29,null,null,2,8,6,33,81,null,38,1,23,34,63,64,39,5,68,84,21,null,6,null,null,null,23,null,65,null,89,null,2,88,90,null,92,null,null,null,79,null,8,90,6,null,null,null,67,null,null,7,87,44,57,null,null,null,21,null,null,null,17,66,67,null,null,null,18,null,27,56,17,null,1,30,43,28,24,47,3,null,99,null,60,69,27,null,20,null,null,null,65,84,79,null,91,null,33,null,null,null,11,null,18,null,null,null,null,84,73,null,null,94,57,13,null,null,null,null,12,86,93,null,22,null,null,null,34,40,10,null,null,null,78,60,79,null,32,67,44,38,10,77,97,null,null,null,null,null,null,null,null,null,null,22,null,null,78,44,85,null,null,null,null,null,85,32,31,null,null,null,null,7,47,5,97,null,20,null,13,null,31,null,70,null,null,null,49,1,63,90,76,null,53,null,99,13,94,null,null,null,8,49,46,null,25,74,7,57,100,7,56,null,null,100,48,null,48,19,84,82,null,31,null,78,34,null,null,null,null,null,50,83,33,null,null,null,36,51,69,93,1,null,null,null,null,6,93,61,94,52,78,null,60,61,49,null,86,88,43,94,55,null,97,2,98,null,51,null,36,null,9,null,null,53,35,17,76,71,65,null,null,null,59,73,null,null,null,null,76,null,null,null,null,null,61,3,15,null,null,null,45,94,6,null,67,null,null,null,null,null,null,28,96,null,null,null,22,null,null,null,null,null,60,null,86,null,100,null,15,null,1,null,null,null,56,86,44,null,null,null,43,null,null,null,26,86,41,null,null,null,null,33,73,null,4,33,30,27,38,34,51,43,43,88,61,null,null,null,null,37,62,null,88,null,null,40,22,null,18,null,null,92,9,null,null,null,40,null,17,null,65,null,null,null,98,null,null,null,null,null,73,null,null,null,74,null,49,null,63,null,26,null,63,75,32,null,84,79,76,null,null,45,null,63,37,null,null,null,null,null,39,null,98,null,38,16,44,42,7,16,91,54,null,80,98,null,62,null,null,69,25,null,null,null,null,36,34,null,null,null,30,9,39,null,17,null,null,null,null,9,97,null,null,null,null,null,19,null,null,67,66,null,93,null,68,70,87,null,null,null,12,47,48,null,21,27,86,null,null,null,95,6,88,null,4,null,43,55,5,49,64,null,null,89,72,79,15,null,null,null,null,null,84,null,60,null,85,null,78,null,null,null,30,null,62,null,35,66,74,null,null,null,null,56,64,null,null,null,null,null,null,15,99,null,null,null,null,67,56,54,null,null,null,99,8,null,null,null,null,29,92,null,99,null,54,null,13,8,16,null,28,53,71,null,null,null,null,59,8,null,15,null,1,null,null,null,67,null,15,null,null,40,66,null,null,null,null,94,93,null,54,61,71,null,null,null,67,null,77,null,55,null,null,31,48,50,20,null,null,null,98,null,56,52,68,null,3,null,null,33,40,null,52,24,39,57,22,null,null,null,null,null,60,null,null,91,90,null,31,null,null,null,85,null,null,null,31,null,29,null,23,null,83,null,32,null,null,57,97,null,null,null,77,null,73,47,64,58,54,null,null,null,null,7,55,null,null,null,null,null,39,72,56,null,2,null,86,100,94,76,19,2,2,null,30,null,89,null,null,100,2,null,null,3,4,null,null,null,15,null,53,null,null,null,43,null,11,null,56,null,null,28,23,null,52,49,86,null,null,null,17,44,63,null,null,30,79,null,90,null,62,null,null,null,48,28,83,85,64,null,null,23,26,null,42,null,79,null,null,null,63,null,null,null,null,null,91,null,31,null,21,null,null,null,null,null,null,null,86,null,56,null,null,82,34,null,7,80,61,26,40,null,39,65,43,null,98,null,90,56,68,null,10,35,90,null,13,90,15,null,57,98,35,null,null,null,37,null,31,33,97,null,50,37,20,null,99,null,null,null,null,97,11,null,61,null,null,null,null,null,null,null,74,null,null,null,null,86,12,null,null,30,35,null,null,37,83,null,null,null,null,53,84,49,85,null,55,100,45,null,null,null,null,null,67,null,13,null,26,null,null,null,65,null,76,null,46,1,100,null,76,null,null,null,50,52,null,null,30,null,57,null,96,null,93,null,null,null,null,75,36,null,null,70,48,null,null,null,9,null,8,33,40,65,47,null,82,null,64,null,null,null,25,null,84,null,98,null,null,10,49,44,null,null,48,null,null,null,null,null,null,null,57,null,null,null,null,null,39,null,null,72,22,52,33,null,28,null,24,null,76,20,37,6,32,null,1,null,null,null,65,null,11,null,null,null,null,null,74,null,21,63,null,null,20,87,57,null,null,null,39,null,null,64,71,null,90,null,null,null,99,null,null,null,45,null,95,24,27,null,null,null,null,null,10,null,52,null,null,null,null,null,53,null,null,null,35,null,98,null,null,null,null,null,41,null,20,null,4,null,56,null,93,70,39,null,21,null,30,null,null,null,51,null,92,null,48,44,19,null,21,null,54,null,6,null,57,77,88,null,null,null,null,57,12,null,40,null,null,null,null,null,30,2,83,null,75,null,71,4,75,69,33,22,56,null,null,null,null,null,null,null,57,null,null,null,13,null,75,null,null,null,null,87,90,null,null,6,82,48,70,78,100,null,87,null,null,null,null,null,79,null,69,null,null,95,76,null,null,null,43,null,null,26,14,null,25,null,null,null,34,null,51,null,null,null,20,19,74,7,85,null,17,null,9,null,null,null,26,null,null,null,90,44,56,9,33,null,null,null,47,null,null,null,null,null,null,null,null,null,null,88,11,null,null,null,null,47,29,null,57,null,88,null,98,14,21,30,null,null,66,65,51,null,null,null,46,null,null,null,9,null,null,95,60,null,null,null,90,null,100,73]
7*/
public class Question1530 {
    /**
     * 找出来全部叶子节点的高和宽
     * @param root 根节点
     * @param height_leaves 叶子的高度
     * @param width_leaves 叶子的宽度
     * @param cur_height 当前节点的高度
     * @param cur_width 当前节点的宽度
     */
    private void findAllLeaves(TreeNode root, ArrayList<Integer> height_leaves, ArrayList<Integer> width_leaves, int cur_height , int cur_width){
        int num_null = 0;
        if(root.left!=null){
            findAllLeaves(root.left,height_leaves,width_leaves,cur_height+1, cur_width*2);
        }
        else{
            num_null++;
        }
        if(root.right!=null){
            findAllLeaves(root.right, height_leaves, width_leaves, cur_height+1, cur_width*2+1);
        }
        else{
            num_null++;
        }
        if(num_null==2){
            height_leaves.add(cur_height);
            width_leaves.add(cur_width);
        }
    }

    /**
     * 根据两片叶子的高度和宽度计算其距离
     * @param h1 第一片叶子的高度
     * @param w1 。。。的宽度
     * @param h2 第二片叶子的高度
     * @param w2 。。。的宽度
     * @return 二者距离
     */
    private int getDist(int h1, int w1, int h2, int w2){
        /*二者距离*/
        int dist = 0;
        /*先把二者搞到同一个高度，高度每差1，距离加1*/
        if(h1>h2){
            while(h1>h2){
                w1/=2;
                h1--;
                dist++;
            }
        }
        else{
            while(h2>h1){
                w2/=2;
                h2--;
                dist++;
            }
        }
        /*再考虑二者因为高度相同，但是祖先不同造成的距离*/
        /*二者每差一层，就需要同时除一次2才能相等，每一次距离都会加2*/
        while(w1!=w2){
            w1/=2;
            w2/=2;
            dist+=2;
        }
        return dist;
    }

    public int countPairs(TreeNode root, int distance) {
        /*先找出所有的节点，并且知道其高度，宽度*/
        /*全部叶子节点的高度*/
        ArrayList<Integer> height = new ArrayList<>();
        /*全部叶子节点的宽度（考虑满二叉树的时候，这个节点是这一层从左往右第几个）*/
        ArrayList<Integer> width = new ArrayList<>();
        /*把全部叶子节点的高和宽放进去*/
        this.findAllLeaves(root, height, width,0,0);
        /*再根据高度和宽度，快速判断其距离*/
        /*如果只有一片叶子，直接返回零*/
        if(width.size()==1)return 0;
        int i,j, num = width.size();
        /*记录总共有多少片叶子距离合格*/
        int sum = 0;
        for(i=0;i<num-1;i++){
            for(j=i+1;j<num;j++){
                if(this.getDist(height.get(i),width.get(i),height.get(j),width.get(j))<=distance){
                    sum++;
                }
            }
        }
        return sum;
    }
}
