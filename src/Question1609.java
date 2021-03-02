import java.util.LinkedList;

public class Question1609 {
    /**
     * 判断一棵树是不是奇偶树
     * 1. 根的层数是0，往下是1，以此类推
     * 2. 层数是偶数的，每一个节点的值是奇数，而且从左到右单调递增
     * 3. 层数是奇数的，每一个节点的值是偶数，从右到左单调递减
     * */
    public boolean isEvenOddTree(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> level = new LinkedList<>();
        queue.addLast(root);
        level.addLast(0);
        int current_level = 0;
        int former_value = Integer.MIN_VALUE;
        while(!queue.isEmpty()&&!level.isEmpty()){
            int node_level = level.pollFirst();
            TreeNode node = queue.pollFirst();
            if(node_level!=current_level){
                former_value = node.val;
                if((former_value % 2 == 0) == (node_level % 2 == 0)){
                    return false;
                }
                current_level = node_level;
            }else{
                if(current_level%2==0){//偶数层
                    if(node.val%2!=1)return false;//不是奇数
                    if(node.val<=former_value)return false;//不是单调递增
                }else{//奇数层
                    if(node.val%2!=0)return false;//不是偶数
                    if(node.val>=former_value)return false;//不是单调递减
                }
                former_value = node.val;
            }
            if(node.left!=null){
                queue.addLast(node.left);
                level.addLast(node_level+1);
            }
            if(node.right!=null){
                queue.addLast(node.right);
                level.addLast(node_level+1);
            }
        }
        return true;
    }
}
