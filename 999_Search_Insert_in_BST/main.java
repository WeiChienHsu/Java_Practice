// Insert val - Iteration

public TreeNode search(TreeNode root, int val) {
    if(root == null || root.val == val) {
        return root;
    }

    TreeNode cur = root;

    while(cur != null) {
        if(cur.val = val) {
            return cur;
        } else if(cur.val < val) {
            cur = cur.right;;
        } else {
            cur = cur.left
        }
    }
    return null; // curr = null
}

// Recursion

public TreeNode search(TreeNode root, int val) {
    if(root == null || root.val = val) {
        return root;
    }

    else if(root.val < val) {
        return search(root.right, val);
    } else {
        return search(root.left, val)
    }
}