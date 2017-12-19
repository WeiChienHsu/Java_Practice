public TreeNode upsideDownBinaryTree(TreeNode root) {
    if(root == null) {
        return root;
    }

    Deque<TreeNode> stack = new ArrayDeque<>();

    // Pull left nodes into Stack

    while (root != null) {
        stack.offerLast(root);
        root = root.left;
    }

    // Set new Root for the Tree
    TreeNode newRoot = stack.pollLast();
    TreeNode cur = newRoot;

    // Change Pointers
    while(!stack.isEmpty()){
        TreeNode oriParent = stack.pollLast(); 
        cur.right = oriParent;
        cur.left = oriParent.right; //original right becomes new left
        
        cur = oriParent;
        oriParent.left = null;
        oriParent.right = null;
    }
  
    return newRoot;
}