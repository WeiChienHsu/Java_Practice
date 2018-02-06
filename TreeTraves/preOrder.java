
public List<Integer> preorderTraversal(TreeNode root) {
  ArrayDeque<TreeNode> stack = new ArrayDeque<>();
  List<Integer> preorder = new ArrayList<>();
  
  if(root == null) {
      return preorder;
  }
  
  stack.offerLast(root);
  
  while(!stack.isEmpty()) {
      TreeNode node = stack.pollLast();
      preorder.add(node.val);
      
      if(node.right != null){
          stack.offerLast(node.right);
      }
      
      if(node.left != null) {
          stack.offerLast(node.left);
      }
  }
  
  return preorder;
}

// ++++++++++++++++++++++++++++++++++++++++

  public List<Integer> preorderTraversal(TreeNode root) {
      List<Integer> preorder = new ArrayList<>();
      traverse(root, preorder);
      return preorder;
  }
  
  private void traverse(TreeNode root, List<Integer> preorder) {
      if(root == null) {
          return; 
      }
      preorder.add(root.val);
      traverse(root.left, preorder);
      traverse(root.right, preorder);
  }