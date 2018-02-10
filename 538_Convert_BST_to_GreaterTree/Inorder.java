class Solution {
  int sum;
  
  public TreeNode convertBST(TreeNode root) {
      sum = 0;
      helper(root);
      return root;
  }
  
  public void helper(TreeNode root) {
      if(root == null) {
          return;
      }
      
      helper(root.right);
      root.val += sum;
      sum = root.val;
      helper(root.left);
  }
}