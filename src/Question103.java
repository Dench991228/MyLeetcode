import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Question103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if(root==null)return result;
        LinkedList<TreeNode>[] lists = new LinkedList[2];
        lists[0] = new LinkedList<>();
        lists[1] = new LinkedList<>();
        int flag = 0;
        lists[flag].addLast(root);
        while(!(lists[0].isEmpty()&&lists[1].isEmpty())){
            List<Integer> current_line = new LinkedList<>();
            result.add(current_line);
            while(!lists[flag].isEmpty()){
                TreeNode current = lists[flag].pollFirst();
                current_line.add(current.val);
                if(flag==0){//这一行从左往右
                    if(current.left!=null)lists[flag^1].addFirst(current.left);
                    if(current.right!=null)lists[flag^1].addFirst(current.right);
                }else{//这一行从右往左
                    if(current.right!=null)lists[flag^1].addFirst(current.right);
                    if(current.left!=null)lists[flag^1].addFirst(current.left);
                }
            }
            flag^=1;
        }
        return result;
    }
}
