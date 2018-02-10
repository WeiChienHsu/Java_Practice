public TreeNode invertTree(TreeNode root) {
  if(root == null) {
      return null;
  }
  
  TreeNode tempRight = root.right;
  root.right = invertTree(root.left);
  root.left = invertTree(tempRight);
  return root;
}
}