import java.util.LinkedList;

public class Question297 {

    public String serialize(TreeNode root) {
        StringBuilder result = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while(!queue.isEmpty()){
            TreeNode current_node = queue.pollFirst();
            if(current_node!=null){
                result.append(current_node.val).append(",");
                queue.addLast(current_node.left);
                queue.addLast(current_node.right);
            }
            else{
                result.append("null,");
            }
        }
        //消除最后的逗号
        result.substring(0, result.length()-1);
        //加上括号
        result.insert(0,"[");
        result.append("]");
        return result.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] input = data.substring(1, data.length()-1).split(",");
        if(input[0]==null)return null;
        TreeNode root = new TreeNode(Integer.parseInt(input[0]));
        LinkedList<TreeNode> fathers = new LinkedList<>();//用来保存等待配对的父节点
        int current_position = 1;
        fathers.addLast(root);
        while(!fathers.isEmpty()){
            TreeNode current_node = fathers.pollFirst();
            if(input[current_position].compareTo("null")!=0){
                TreeNode left_children = new TreeNode(Integer.parseInt(input[current_position]));
                current_node.left = left_children;
                fathers.addLast(left_children);
            }
            current_position ++;
            if(input[current_position].compareTo("null")!=0){
                TreeNode right_children = new TreeNode(Integer.parseInt(input[current_position]));
                current_node.right = right_children;
                fathers.addLast(right_children);
            }
            current_position ++;
        }
        return root;
    }
}
