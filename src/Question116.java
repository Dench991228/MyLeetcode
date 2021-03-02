import java.util.LinkedList;

public class Question116 {
    public Node connect(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        LinkedList<Integer> level = new LinkedList<>();
        queue.addLast(root);
        level.addLast(0);
        Node former = null;
        int this_level = -1;
        while(!queue.isEmpty()&&!level.isEmpty()){
            Node current_node = queue.pollFirst();
            int current_level = level.pollFirst();
            if(current_level!=this_level){
                this_level = current_level;
            }else{
                former.next = current_node;
            }
            former = current_node;
            if(current_node.left!=null){
                queue.addLast(current_node.left);
                level.addLast(current_level+1);
            }
            if(current_node.right!=null){
                queue.addLast(current_node.right);
                level.addLast(current_level+1);
            }
        }
        return root;
    }
}
