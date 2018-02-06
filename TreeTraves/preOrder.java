/* Iteration */
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
/* Recursion */
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

// +++++++++++++++++++++++++++++++++++++++++
/* Divide and Conquer */

public List<Integer> preorderTraversal(TreeNode root) {
  ArrayList<Integer> res = new ArrayList<>();
  
  if(root == null) {
      return res;
  }
  
  // Divide
  List<Integer> left = preorderTraversal(root.left); //[2, 4, 5]
  List<Integer> right = preorderTraversal(root.right); //[3]
  
  // Conquer (Merge)
  res.add(root.val);
  res.addAll(left);
  res.addAll(right);
  
  return res; // [1] + [2,4,5] + [3]
}