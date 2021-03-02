import java.util.LinkedList;
import java.util.List;

public class Question102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
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
                if(current.left!=null)lists[flag^1].addLast(current.left);
                if(current.right!=null)lists[flag^1].addLast(current.right);
            }
            flag^=1;
        }
        return result;
    }
}
