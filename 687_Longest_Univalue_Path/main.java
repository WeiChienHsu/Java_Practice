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
  public int maxHeight = 0;
  public int longestUnivaluePath(TreeNode root) {
      if(root == null) return 0;
      getHeight(root, root.val);
      return maxHeight;
  }
  
  public int getHeight(TreeNode node, int val) {
      if(node == null) return 0;
      //  Get the Height from left and right
      int left = getHeight(node.left, node.val);
      int right = getHeight(node.right, node.val);
      // if node value == fater node value -> return Max(right or left) 
      // if node value != father node value -> return 0
      // Record the current Hieght(Since the left and right always equal to their father's value or they wont be greater than 0)
      maxHeight = Math.max(left + right, maxHeight);
      if(val == node.val) return Math.max(left, right) + 1;
      return 0;
  }
}