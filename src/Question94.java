import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Question94 {
    public static List<Integer> inorderTraversal(TreeNode root){
        ArrayList<Integer> result=new ArrayList<>();
        LinkedList<TreeNode> nodes = new LinkedList<>();//遍历过程中的节点栈
        LinkedList<Boolean> visited = new LinkedList<>();//遍历过程中的是否访问过的栈
        TreeNode cur = root;//当前正在访问的节点
        boolean flag = true;//遍历是否结束
        while(flag&&cur!=null){
            while(cur.left!=null){
                nodes.addLast(cur);
                visited.addLast(Boolean.FALSE);
                cur = cur.left;
            }
            /*此时，这个节点已经是一个没有左子树的节点了，而且不在堆栈中*/
            nodes.addLast(cur);
            visited.addLast(Boolean.TRUE);
            result.add(cur.val);
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
                        result.add(cur.val);
                    }
                }
                //如果还有东西，那就表明它的左子树刚刚搞定，接下来是右子树
                if(!visited.isEmpty()){
                    cur = nodes.peekLast();
                    visited.pollLast();
                    visited.addLast(Boolean.TRUE);
                    result.add(cur.val);
                    cur = cur.right;
                }
                else{
                    flag=false;
                }
            }
        }
        return result;
    }
}
