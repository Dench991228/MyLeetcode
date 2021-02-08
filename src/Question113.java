import java.util.LinkedList;
import java.util.List;

public class Question113 {
    /**
     * 递归的遍历整棵树可能的路径，顺便记录正好是target的路径
     * @param current_path 当前已经走过的路径
     * @param collection 符合要求的路径
     * @param root 被遍历的子树的根节点
     * @param target 目标路径和
     * */
    private void findPath(LinkedList<Integer> current_path, List<List<Integer>> collection, TreeNode root, int target){
        current_path.addLast(root.val);
        if(target==root.val&&(root.left==null&&root.right==null)){
            collection.add(new LinkedList<>(current_path));
        }else{
            if(root.left!=null){
                findPath(current_path, collection, root.left, target - root.val);
            }
            if(root.right!=null){
                findPath(current_path, collection, root.right, target - root.val);
            }
        }
        current_path.pollLast();
    }
    /**
     * 找到从根节点到叶子节点值之和是targetSum的路径，并且加入到结果中
     * */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> current_path = new LinkedList<>();
        findPath(current_path, result, root, targetSum);
        return result;
    }
}
