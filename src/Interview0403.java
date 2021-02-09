import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class Interview0403 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    private class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
    public ListNode[] listOfDepth(TreeNode tree) {
        ArrayList<ListNode> first_nodes = new ArrayList<>();//saves the first node linked list produced
        ArrayList<ListNode> last_nodes = new ArrayList<>();//saves the last node of those linked list
        LinkedList<TreeNode> queue = new LinkedList<>();// the nodes to be visit
        LinkedList<Integer> depth = new LinkedList<>();// the depth of the elements in queue
        queue.add(tree);
        depth.add(1);
        while(!queue.isEmpty()&&!depth.isEmpty()){
            TreeNode current_node = queue.pollFirst();
            int current_depth = depth.pollFirst();
            if(!(current_depth<=first_nodes.size())){// build a new LinkedList
                ListNode temp = new ListNode(current_node.val);
                first_nodes.add(temp);
                last_nodes.add(temp);
            }else{// add to a existing list
                ListNode new_node = new ListNode(current_node.val);
                last_nodes.get(last_nodes.size()-1).next = new_node;
                last_nodes.remove(last_nodes.size()-1);
                last_nodes.add(new_node);
            }
            if(current_node.left!=null){
                queue.add(current_node.left);
                depth.add(current_depth+1);
            }
            if(current_node.right!=null){
                queue.add(current_node.right);
                depth.add(current_depth+1);
            }
        }
        ListNode[] result = new ListNode[first_nodes.size()];
        int i = 0;
        for(ListNode n:first_nodes){
            result[i] = n;
            i++;
        }
        return result;
    }
}
