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
  public int closestValue(TreeNode root, double target) {
      int result = 0;
      double minDiff = Double.MAX_VALUE;
      
      while(root != null) {
          double tempDiff = Math.abs(target - root.val);
          if(tempDiff < minDiff) {
              minDiff = tempDiff;
              result = root.val;
          }
          
          if(root.val > target) root = root.left;
          else root = root.right;
      }
      
      return result;
      
  }
}