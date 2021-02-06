import sun.awt.image.ImageWatched;

import java.util.LinkedList;

public class Question230 {
    /**
     * 中序遍历二叉搜索树，尝试找到第k小的数
     * @param root 树根
     * @param numbers 当前已经找到的整数，如果已经有k个了就收手
     * @param k k
     * */
    private void dfs(LinkedList<Integer> numbers, TreeNode root, int k){
        if(numbers.size()>=k)return;
        if(root.left!=null)dfs(numbers, root.left, k);
        numbers.addLast(root.val);
        if(numbers.size()>=k)return ;
        if(root.right!=null)dfs(numbers, root.right, k);
    }
    /**
     * 中序遍历，只要找到第k个数，就输出即可
     * @param root 二叉搜索树的树根
     * @param k 第k小的数
     * */
    public int kthSmallest(TreeNode root, int k) {
        LinkedList<Integer> numbers = new LinkedList<>();
        dfs(numbers, root, k);
        return numbers.get(k);
    }
}
