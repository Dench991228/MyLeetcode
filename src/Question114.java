import sun.reflect.generics.tree.Tree;

public class Question114 {
    /**
     * 把整个树扁平化。
     * 先获得左子树的最后一个节点，然后让它连接到右子树上
     * @param t 树根
     * @return 一棵树上前序遍历的最后一个节点
     */
    private TreeNode getFormer(TreeNode t){
        //如果是叶子节点，那么就是自己
        if(t.left==null&&t.right==null){
            return t;
        }
        TreeNode leftTree = t.left;//左子树树根
        TreeNode rightTree = t.right;//右子树树根
        TreeNode leftTreeEnd = new TreeNode(Integer.MIN_VALUE);//左子树前序遍历最后一个节点
        TreeNode rightTreeEnd = new TreeNode(Integer.MIN_VALUE);//右子树前序遍历最后一个节点
        TreeNode result = new TreeNode(Integer.MAX_VALUE);
        if(leftTree!=null){
            leftTreeEnd = this.getFormer(leftTree);
            t.right = leftTree;
            t.left = null;
            leftTreeEnd.left=null;
            leftTreeEnd.right = rightTree;
            if(rightTree!=null)result = this.getFormer(rightTree);
        }
        else{
            result = this.getFormer(rightTree);
        }
        return result;
    }
    /**
     * 把二叉树转换成链表，按照前序遍历顺序
     * @param root 二叉树树根
     */
    public void flatten(TreeNode root) {
        this.getFormer(root);
    }
}
