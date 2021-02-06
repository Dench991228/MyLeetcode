import java.util.LinkedList;

public class Question1379 {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if(original==null&&cloned==null)return null;
        int target_number = target.val;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(cloned);
        while(!queue.isEmpty()){
            TreeNode current_node = queue.pollFirst();
            if(current_node.val == target_number)return current_node;
            if(current_node.left!=null) queue.addLast(current_node.left);
            if(current_node.right!=null) queue.addLast(current_node.right);
        }
        return null;
    }
}
