class Solution {
  public int rob(TreeNode root) {
      int[] res = robSub(root);
      return Math.max(res[0], res[1]);
  }
  
  private int[] robSub(TreeNode root) {
      if(root == null) return new int[2];
      int[] res = new int[2];
      int[] left = robSub(root.left);
      int[] right = robSub(root.right);
      
      // [Rob, NotRob]
      
      res[0] = root.val + left[1] + right[1];
      res[1] = Math.max(left[1], left[0]) + Math.max(right[1], right[0]);
      return res;
  }
}