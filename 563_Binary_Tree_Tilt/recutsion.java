class Solution {
  public int findTilt(TreeNode root) {
      if(root == null) return 0;
      int curVal = Math.abs(sumOfSubtree(root.left) - sumOfSubtree(root.right));
      return curVal + findTilt(root.left) + findTilt(root.right);
  }
  
  public int sumOfSubtree(TreeNode root) {
      if(root == null) return 0;
      return root.val + sumOfSubtree(root.left) + sumOfSubtree(root.right);
  }
}