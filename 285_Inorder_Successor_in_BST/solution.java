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
  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
      List<TreeNode> inorder = new ArrayList<>();
      helper(root, inorder);
      for(int i = 0; i < inorder.size() - 1; i++) {
          if(inorder.get(i).val == p.val) {
              return inorder.get(i + 1);
          }
      }
      return null;
  }
  
  public void helper(TreeNode root, List<TreeNode> inorder) {
      if(root == null) return;
      helper(root.left, inorder);
      inorder.add(root);
      helper(root.right, inorder);
  }
  
}