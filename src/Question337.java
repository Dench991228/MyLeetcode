import java.util.HashMap;

public class Question337 {
    /**
     * 考虑以某个节点为根的子树上，最多能打劫出多少钱
     * @param within 记录如果包含某个节点，则以其为根的子树上最多可以打劫多少钱
     * @param without 记录如果不包含某个节点，以其为根的子树上最多可以打劫多少钱
     * @param t 根节点
     */
    private void getDp(HashMap<TreeNode,Integer> within, HashMap<TreeNode,Integer> without,TreeNode t){
        /*考虑t是叶子的情况*/
        if(t.left==null&&t.right==null){
            within.put(t,t.val);
            without.put(t,0);
            return;
        }
        int sumWithin = 0;
        int sumWithout = 0;
        /*先考虑不包含本节点的情况*/
        sumWithout += (t.left==null?0:Math.max(within.get(t.left),without.get(t.left)));
        sumWithout += (t.right==null?0:Math.max(within.get(t.right),without.get(t.right)));
        without.put(t,sumWithout);
        /*考虑包括本节点的情况*/
        /*先考虑左子树*/
        if(t.left!=null){
            /*不打劫左儿子，改为打劫它的左左*/
            sumWithin += (t.left.left==null?0:Math.max(within.get(t.left.left),without.get(t.left.left)));
            /*不打劫左儿子，但是要光顾它的右儿子*/
            sumWithin += (t.left.right==null?0:Math.max(within.get(t.left.right),without.get(t.left.right)));
        }
        /*再考虑右子树*/
        if(t.right!=null){
            sumWithin += (t.right.left==null?0:Math.max(within.get(t.right.left),without.get(t.right.left)));
            sumWithin += (t.right.right==null?0:Math.max(within.get(t.right.right),without.get(t.right.right)));
        }
        within.put(t,sumWithin);
    }

    /**
     * 遍历一棵树，并且存储所有的相关信息
     * @param t
     */
    private void runThrough(HashMap<TreeNode,Integer> within, HashMap<TreeNode,Integer> without, TreeNode t){
        if(t.left!=null)runThrough(within,without,t.left);
        if(t.right!=null)runThrough(within,without,t.right);
        this.getDp(within,without,t);
    }
    public int rob(TreeNode root) {
        HashMap<TreeNode,Integer> within = new HashMap<>();
        HashMap<TreeNode,Integer> without = new HashMap<>();
        this.runThrough(within,without,root);
        return Math.max(within.get(root),without.get(root));
    }
}
