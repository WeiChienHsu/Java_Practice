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
  public boolean isSubtree(TreeNode s, TreeNode t) {
      if(s == null) return false;
      if(haveSameValue(s, t)) return true;
      return isSubtree(s.left, t) || isSubtree(s.right, t); // 左右皆有可能含有 Subtree
  }
  
  public boolean haveSameValue(TreeNode s, TreeNode t) {
      if(s == null && t == null) return true;
      if(s == null || t == null) return false;
      if(s.val != t.val) return false;
      return haveSameValue(s.left, t.left) && haveSameValue(s.right, t.right); // 左與右的值要相同，才能構成 Subtree
  }
}