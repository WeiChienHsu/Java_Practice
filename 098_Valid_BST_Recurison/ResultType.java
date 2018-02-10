class Solution {
  public boolean isValidBST(TreeNode root) {
      ResultType rt = helper(root);
      return rt.isBalanced;
  }
  
  private ResultType helper(TreeNode root) {
      if(root == null) {
          return  new ResultType(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
      }
      
      ResultType left = helper(root.left);
      ResultType right = helper(root.right);
      
      if(!left.isBalanced || ! right.isBalanced) {
          return new ResultType(false, 0, 0);
      }
      
      if((root.left != null && left.maxValue >= root.val) ||
        (root.right != null && right.minValue <= root.val)){
          return new ResultType(false,0, 0);
      }
      
      return new ResultType(true, Math.max(root.val, right.maxValue), Math.min(root.val, left.minValue));
  }
  
  class ResultType{
      boolean isBalanced;
      int maxValue;
      int minValue;
      public ResultType(boolean ibt, int max, int min) {
          this.isBalanced = ibt;
          this.maxValue = max;
          this.minValue = min;
      }
  }