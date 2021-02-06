import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Question95 {
    /*含有n个节点的树：
    * 含有n-1个节点的树，把根换成n
    * 含有n-1个节点的树，把根之外的其他右子树上的节点换成n
    * 含有n-1个节点的树，插入n
    * */
    /*
    * 复制一棵树
    * @param t 树的根节点
    * @return 新的已经复制好的树
    * */
    private TreeNode copyTree(TreeNode t){
        if(t==null)return null;
        else return new TreeNode(t.val,this.copyTree(t.left),this.copyTree(t.right));
    }
    /*
    * 把一棵树的一个节点的右子树换成新的节点，返回新的树，原来的树结构没有破坏
    * @param num 新节点的值
    * @param t 锚点（不是null）
    * @param root 树根
    * */
    private TreeNode replace(TreeNode root,TreeNode t, int num){
        TreeNode new_tree = this.copyTree(root);
        TreeNode n = new_tree;
        /*让n在自己的树上找到与t对应的节点*/
        while(n.val!=t.val){
            if(n.val<t.val)n=n.right;
            else n=n.left;
        }
        TreeNode nn = new TreeNode(num);
        nn.left = n.right;
        n.right = nn;
        return new_tree;
    }
    /*
    * 给一棵树换一个根，返回新的树，原来的树结构没有破坏
    * @param root 根
    * @param num 新节点的值
    * */
    private TreeNode replaceRoot(TreeNode root, int num){
        TreeNode new_root = this.copyTree(root);
        TreeNode t = new TreeNode(num);
        t.left = new_root;
        return t;
    }
    /*
    * 在一棵树种插入一个新节点，不管原来的树
    * @param root 根
    * @param num 新节点的值
    * */
    private void insertNode(TreeNode root, int num){
        TreeNode t = root;
        while(t!=null&&t.val<root.val){
            if(t.right==null){
                t.right = new TreeNode(num);
                break;
            }
            t=t.right;
        }
    }
    public List<TreeNode> generateTrees(int n) {
        ArrayList<TreeNode> former = new ArrayList<>();
        if(n==0)return former;
        former.add(new TreeNode(1));
        if(n==1)return former;
        ArrayList<TreeNode> cur = new ArrayList<>();
        int i;
        for(i=2;i<=n;i++){
            for(TreeNode root:former){//当前正在遍历的树
                cur.add(this.replaceRoot(root,i));
                TreeNode t = root;
                while(t!=null){//树上各个节点的右子树
                    if(t.right==null){
                        this.insertNode(root,i);
                        cur.add(root);
                    }
                    else{
                        cur.add(this.replace(root,t,i));
                    }
                    t=t.right;
                }
            }
            former = cur;
            cur = new ArrayList<>();
        }
        return former;
    }
}