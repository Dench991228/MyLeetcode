public class Question100 {
    private boolean checkSame(TreeNode p,TreeNode q){
        boolean leftResult = true;
        /*先判断两棵树当前节点是否相等*/
        if(p.val!=q.val)return false;
        /*左侧相关*/
        if(p.left!=null&&q.left!=null){
            leftResult=checkSame(p.left,q.left);
        }
        else if(p.left==null&&q.left==null){

        }
        else{
            return false;
        }
        /*右侧相关*/
        if(p.right!=null&&q.right!=null){
            return leftResult&&checkSame(p.right,q.right);
        }
        else if(p.right==null&&q.right==null){

        }
        else{
            return false;
        }
        return true;
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return this.checkSame(p,q);
    }
}
