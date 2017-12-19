public TreeNode upsideDownBinaryTree(TreeNode root) {
    if(root == null || root.left == null) {
        return root;
    }

    // Assume all lower levels are handle
    TreeNode newRoot = upsideDownBinaryTree(root.left); 

    // Handle current level

    root.left.left = root.right;
    root.left.right = root;
    root.legt = null;
    root.right = null;

    return newRoot;
}