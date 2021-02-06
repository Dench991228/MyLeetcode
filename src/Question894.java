import java.util.LinkedList;
import java.util.List;

public class Question894 {
    private void copyTree(TreeNode root, TreeNode new_root){
        if(root.left!=null){
            TreeNode t = new TreeNode(0);
            new_root.left = t;
            copyTree(root.left,new_root.left);
        }
        if(root.right!=null){
            TreeNode t = new TreeNode(0);
            new_root.right = t;
            copyTree(root.right,new_root.right);
        }
    }
    private void tryFindTree(TreeNode root, TreeNode cur_root, int num_node, List<TreeNode> result){
        if(num_node == 0){
            TreeNode new_root = new TreeNode(0);
            this.copyTree(root,new_root);
            result.add(new_root);
        }
        else if(num_node>=2){
            TreeNode l = new TreeNode(0);
            TreeNode r = new TreeNode(0);
            cur_root.left = l;
            cur_root.right = r;
            tryFindTree(root, cur_root.left,num_node-2, result);
            tryFindTree(root, cur_root.right,num_node-2, result);
        }
        else{
            return;
        }
    }
    public List<TreeNode> allPossibleFBT(int N) {
        LinkedList<TreeNode> result = new LinkedList<>();
        return result;
    }
}
