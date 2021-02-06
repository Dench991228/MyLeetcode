import java.util.LinkedList;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
 }
public class Question958 {
    /**
     * 判断一二搜索树是不是完全二叉树
     * 按照从上到下，从左到右给每个节点编号
     * 广度优先，先左后右遍历
     * 记录之前访问的最后一个节点的编号，如果现在扩展出来的和以前的不相邻，那就返回false
     * @param root 树根
     * @return 这棵树是不是完全二叉树
     */
    public boolean isCompleteTree(TreeNode root) {
        int lasVal = 1;//最后一个找到的节点的编号
        LinkedList<TreeNode> new_node = new LinkedList<>();//各个节点的引用
        LinkedList<Integer> num_node = new LinkedList<>();//各个节点的编号
        new_node.addLast(root);
        num_node.addLast(1);
        while(!new_node.isEmpty()&&!num_node.isEmpty()){
            TreeNode t = new_node.pollFirst();
            int current = num_node.pollFirst();
            if(t.left!=null){
                new_node.addLast(t.left);
                num_node.addLast(current*2);
                if(current*2!=lasVal+1)return false;
                lasVal=current*2;
            }
            if(t.right!=null){
                new_node.addLast(t.right);
                num_node.addLast(current*2+1);
                if(current*2+1!=lasVal+1)return false;
                lasVal=current*2+1;
            }
        }
        return true;
    }
}
