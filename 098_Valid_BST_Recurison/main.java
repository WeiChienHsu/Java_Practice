public boolean isValidBST(TreeNode root) {
    if(root = null) {
        return true;
    }
    return isValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE);
}

private boolean isValidBST(TreeNode root, long max, long min) {
    if (root == null) {
        return true;
    }

    //Current Level : Check root.val

    if(root.val >= max || root.val <= min) {
        return false;
    }

    // Return Left and Right
    return isValidBST(root.left, root.val, min) && isValidBST(root.right, max, root.val); 
}