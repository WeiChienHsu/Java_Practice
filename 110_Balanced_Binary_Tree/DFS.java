class Solution {
  public boolean isBalanced(TreeNode root) {
      if(root == null) return true;
      int left = height(root.left);
      int right = height(root.right);
      
      return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
  }
  
  private int height(TreeNode root) {
      if(root == null) return 0;
      int left = height(root.left);
      int right = height(root.right);
      
      return Math.max(left, right) + 1;
  }
}


class Solution {
    public boolean isBalanced(TreeNode root) {
        return maxDepth(root) != -1;
    }
    
    private int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if(left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        
        return Math.max(right, left) + 1;
    }
}