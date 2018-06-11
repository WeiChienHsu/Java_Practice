class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (right == 0 || left == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }
}