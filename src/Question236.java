import java.util.LinkedList;

public class Question236 {
    private boolean getFathers(TreeNode root, LinkedList<TreeNode> ancestor, TreeNode t){
        if(root==t){
            return true;
        }else{
            boolean left = false, right = false;
            if(root.left!=null){
                ancestor.addLast(root.left);
                left = getFathers(root.left, ancestor, t);
                if(left){
                    return true;
                }else{
                    ancestor.pollLast();
                }
            }
            if(root.right!=null){
                ancestor.addLast(root.right);
                right = getFathers(root.right, ancestor, t);
                if(right){
                    return true;
                }else{
                    ancestor.pollLast();
                }
            }
            return false;
        }
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> p_fathers = new LinkedList<>();
        getFathers(root, p_fathers,p);
        LinkedList<TreeNode> q_fathers = new LinkedList<>();
        getFathers(root, q_fathers,q);
        TreeNode ancestor = null;
        while(!p_fathers.isEmpty()&&!q_fathers.isEmpty()&&p_fathers.peekFirst()==q_fathers.peekFirst()){
            ancestor = p_fathers.pollFirst();
            q_fathers.pollFirst();
        }
        return ancestor;
    }
}
