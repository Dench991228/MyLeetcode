import sun.reflect.generics.tree.Tree;

import java.util.HashMap;

public class Question968 {
    /**
     * 获取一个节点如果不放摄像头和放摄像头分别需要多少摄像头
     * @param t 目标节点
     * @param without 不放摄像头记录表
     * @param within 放摄像头记录表
     */
    private void getDp(TreeNode t, HashMap<TreeNode, Integer> without, HashMap<TreeNode,Integer> within){
        if(t.left==null&&t.right==null){//叶子结点
            within.put(t,1);
            without.put(t,0);
            return;
        }
        /*这个点放摄像头，总共需要多少*/
        int sumWithin = 1;
        /*这个点不放摄像头需要多少*/
        int sumWithout = 0;
        /*先获取左边节点的信息*/
        if(t.left!=null&&!within.containsKey(t.left)){
            this.getDp(t.left,without,within);
        }
        /*获取右边节点的相关信息*/
        if(t.right!=null&&!within.containsKey(t.right)) {
            this.getDp(t.right, without, within);
        }
        /*先考虑当前节点不设置摄像头的情况*/
        if(t.left!=null&&t.right!=null){//左右都不空
            /*左边和右边必须有一个*/
            int MustRight = within.get(t.right)+without.get(t.left)==0?1:Math.min(without.get(t.left),within.get(t.left));//右边必放摄像头，左边可放可不放
            int MustLeft = within.get(t.left)+without.get(t.right)==0?1:Math.min(without.get(t.right),within.get(t.right));//左边必有摄像头，右边可有可没有
            sumWithout += Math.min(MustLeft,MustRight);
        }
        else{//左右一个空
            if(t.left==null){//左边没有
                sumWithout += within.get(t.right);
            }
            else{//右边没有
                sumWithout += within.get(t.left);
            }
        }
        /*再考虑当前节点设置摄像头的情况*/
        /*如果有左节点，那就是左节点之下两个节点尽可能少摄像头的情况*/
        if(t.left!=null){
            /*左节点被下面的摄像头监视*/
            int leftCounted = Math.min(within.get(t.left),without.get(t.left)==0?1:without.get(t.left));
            /*左节点不被下面的摄像头监视*/
            int leftNotCounted = 0;
            leftNotCounted += t.left.left==null?0:Math.min(within.get(t.left.left),without.get(t.left.left)==0?Integer.MAX_VALUE:without.get(t.left.left));
            leftNotCounted += t.left.right == null?0:Math.min(within.get(t.left.right),without.get(t.left.right)==0?Integer.MAX_VALUE:without.get(t.left.right));
            sumWithin += Math.min(leftCounted,leftNotCounted);
        }
        /*同上*/
        if(t.right!=null){
            int rightCounted = Math.min(within.get(t.right),without.get(t.right)==0?1:without.get(t.right));
            int rightNotCounted = 0;
            rightNotCounted += t.right.left==null?0:Math.min(within.get(t.right.left),without.get(t.right.left)==0?Integer.MAX_VALUE:without.get(t.right.left));
            rightNotCounted += t.right.right == null?0:Math.min(within.get(t.right.right),without.get(t.right.right)==0?Integer.MAX_VALUE:without.get(t.right.right));
            sumWithin += Math.min(rightCounted,rightNotCounted);
        }
        within.put(t,sumWithin);
        without.put(t,sumWithout);
    }

    /**
     * 遍历一棵树，顺便记录各种信息
     * @param root 这个点放摄像头，需要多少摄像头
     * @param without 这个点不放摄像头，需要多少摄像头
     */
    private void runThrough(TreeNode root, HashMap<TreeNode, Integer> without, HashMap<TreeNode, Integer> within){
        if(root.left!=null)runThrough(root.left,without,within);
        if(root.right!=null)runThrough(root.right,without,within);
        this.getDp(root,without,within);
        System.out.println("within:"+within.get(root));
        System.out.println("without:"+without.get(root));
    }
    public int minCameraCover(TreeNode root) {
        /*记录一个节点上没有摄像头，需要监控以它为根的子树最少多少摄像头*/
        HashMap<TreeNode,Integer> cover_without = new HashMap<>();
        /*记录一个节点上有摄像头，需要监控以它为根的子树最少多少摄像头*/
        HashMap<TreeNode,Integer> cover_within = new HashMap<>();
        this.runThrough(root,cover_without,cover_within);
        System.out.println("within:"+cover_within.get(root));
        System.out.println("without:"+cover_without.get(root));
        return cover_without.get(root)==0?1:Math.min(cover_within.get(root),cover_without.get(root));
    }
}
