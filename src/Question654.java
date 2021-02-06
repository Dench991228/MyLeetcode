public class Question654 {
    /**
     * 将[lower_bound, upper_bound)中的最大值作为树根，树根左边的数按照同样的规则构建，右边同理
     * @param nums 数组
     * @param lower_bound 下边界
     * @param upper_bound 上边界
     * @return 树根
     * */
    private TreeNode constructionInterval(int[] nums, int lower_bound, int upper_bound){
        if(lower_bound == upper_bound)return null;
        int max_number = -1, max_position = 0;
        int length = nums.length;
        for(int i = lower_bound; i < upper_bound; i++){
            if(nums[i] > max_number){
                max_number = nums[i];
                max_position = i;
            }
        }
        TreeNode new_node = new TreeNode(max_number);
        new_node.left = constructionInterval(nums, lower_bound, max_position);
        new_node.right = constructionInterval(nums, max_position+1, upper_bound);
        return new_node;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructionInterval(nums, 0, nums.length);
    }
}
