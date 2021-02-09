import java.util.LinkedList;

public class Question1315 {
    private class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
    public int sumEvenGrandparent(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Boolean> has_even_father = new LinkedList<>();
        int sum = 0;
        queue.add(root);
        has_even_father.add(false);
        while(!queue.isEmpty()&&!has_even_father.isEmpty()){
            TreeNode current = queue.pollFirst();
            boolean flag = has_even_father.pollFirst();
            if(flag){
                sum += current.left==null?0:current.left.val;
                sum += current.right==null?0:current.right.val;
            }
            if(current.left!=null){
                queue.addLast(current.left);
                has_even_father.addLast(current.val%2==0);
            }
            if(current.right!=null){
                queue.addLast(current.right);
                has_even_father.addLast(current.val%2==0);
            }
        }
        return sum;
    }
}
