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
  public int getMinimumDifference(TreeNode root) {
      int[] minDiff = new int[]{Integer.MAX_VALUE};
      if(root == null) return minDiff[0];
      
      List<TreeNode> prev = new ArrayList<>();
      Solution.helper(root, minDiff, prev);
      return minDiff[0];
  }
  
  public static void helper(TreeNode root, int[] minDiff, List<TreeNode> prev) {
      if(root == null) return;
      // Traversal to the Left
      Solution.helper(root.left, minDiff, prev);
      if(prev.isEmpty()) {
          // Haven't add the previous node into array
          prev.add(root);
      } else {
          // Compare the current Node with previous Node
          minDiff[0] = Math.min(minDiff[0], Math.abs(root.val - prev.get(0).val));
          // Update the current Node for the current right child 
          prev.set(0, root); // Replace the existed Node
      }
      // Traversal to the Right
      Solution.helper(root.right, minDiff, prev);
  }
}