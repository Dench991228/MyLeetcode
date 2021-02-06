import java.util.LinkedList;

public class Question1302 {
    public int deepestLeavesSum(TreeNode root) {
        int current_sum = 0;//当前这层节点的和
        TreeNode sentinel = root;//进入下一层的标志，如果是null就表名下一层的节点还没出现
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while(!queue.isEmpty()){
            TreeNode current = queue.pollFirst();
            if(current == sentinel){//如果现在进入了下一层
                current_sum = 0;
                sentinel = null;
            }
            current_sum += current.val;
            if(current.left!=null){
                queue.addLast(current.left);
                if(sentinel==null) sentinel = current.left;
            }
            if(current.right!=null){
                queue.addLast(current.right);
                if(sentinel==null) sentinel = current.right;
            }

        }
        return current_sum;
    }
}
