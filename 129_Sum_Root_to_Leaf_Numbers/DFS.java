/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
  public int sumNumbers(TreeNode root) {
      return dfsHelper(root, 0);
      
  }
  
  public int dfsHelper(TreeNode root, int curSum) {
      
      if(root == null) {
          return 0;
      }
      
      curSum =  curSum * 10 + root.val;
      
      if(root.left == null && root.right == null) {
          return curSum;
      }
      
      int leftSum = dfsHelper(root.left, curSum);  
      int rightSum = dfsHelper(root.right, curSum);
      
      return leftSum + rightSum;
  }
}