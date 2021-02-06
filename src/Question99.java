import java.util.ArrayList;
import java.util.LinkedList;

public class Question99 {
    /*把这棵树线性展开，找到两个下降沿，第一个是下降沿考前的，第二个是靠后的*/
    public void recoverTree(TreeNode root) {
        LinkedList<TreeNode> nodes = new LinkedList<>();//遍历过程中的节点栈
        LinkedList<Boolean> visited = new LinkedList<>();//遍历过程中的是否访问过的栈
        TreeNode early_decline = null;//第一个下降沿，下降沿的前侧有问题
        TreeNode early_tail = null;
        TreeNode latter_decline = null;//第二个下降沿，下降沿的后侧有问题
        TreeNode cur = root;//当前正在访问的节点
        TreeNode former = null;//前一个节点
        boolean flag = true;//遍历是否结束
        /*遍历整棵树，找出下降沿什么的东西*/
        while(flag&&cur!=null){
            while(cur.left!=null){
                nodes.addLast(cur);
                visited.addLast(Boolean.FALSE);
                cur = cur.left;
            }
            /*此时，这个节点已经是一个没有左子树的节点了，而且不在堆栈中*/
            nodes.addLast(cur);
            visited.addLast(Boolean.TRUE);
            /*这个节点被加入，判断和前一个节点的关系*/
            if(former==null){
                former=cur;
            }
            else{
                /*问题节点*/
                if(former.val>cur.val){
                    if(early_decline==null){
                        early_decline=former;
                        early_tail = cur;
                    }
                    else {
                        latter_decline = cur;
                    }
                }
                former = cur;
            }
            /*如果可以，访问右子树*/
            if(cur.right!=null){
                cur = cur.right;
            }
            else{
                while(!visited.isEmpty()&&visited.peekLast()){
                    visited.pollLast();
                    nodes.pollLast();
                    /*没有右子树，左子树搞定的，全部弹出*/
                    while(!visited.isEmpty()&&!visited.peekLast()&&nodes.peekLast().right==null){
                        cur = nodes.pollLast();
                        visited.pollLast();
                        if(former==null){
                            former=cur;
                        }
                        else{
                            /*问题节点*/
                            if(former.val>cur.val){
                                if(early_decline==null){
                                    early_decline=former;
                                    early_tail=cur;
                                }
                                else latter_decline = cur;
                            }
                            former = cur;
                        }
                    }
                }
                //如果还有东西，那就表明它的左子树刚刚搞定，接下来是右子树
                if(!visited.isEmpty()){
                    cur = nodes.peekLast();
                    visited.pollLast();
                    visited.addLast(Boolean.TRUE);
                    if(former==null){
                        former=cur;
                    }
                    else{
                        /*问题节点*/
                        if(former.val>cur.val){
                            if(early_decline==null){
                                early_decline=former;
                                early_tail=cur;
                            }
                            else latter_decline = cur;
                        }
                        former = cur;
                    }
                    cur = cur.right;
                }
                else{
                    flag=false;
                }
            }
        }
        /*只需要交换二者的值即可*/
        //有两次下降沿
        if(latter_decline!=null){
            int early_value = early_decline.val;
            early_decline.val = latter_decline.val;
            latter_decline.val = early_value;
        }
        else{//只有一次下降沿，二者交换即可
            int early_value = early_decline.val;
            early_decline.val = early_tail.val;
            early_tail.val = early_value;
        }
    }
}
