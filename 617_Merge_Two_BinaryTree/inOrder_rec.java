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
  public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
      if(t1 == null && t2 == null) return null;
      
      int newVal = (t1 != null? t1.val : 0) + (t2 != null? t2.val : 0);
      TreeNode newNode = new TreeNode(newVal);
      
      newNode.left = mergeTrees(t1 != null? t1.left : null, t2 != null? t2.left : null);
      newNode.right = mergeTrees(t1 != null? t1.right : null, t2 != null? t2.right : null);
      
      return newNode;
  }
}