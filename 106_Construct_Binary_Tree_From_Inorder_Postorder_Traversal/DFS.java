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
  public TreeNode buildTree(int[] inorder, int[] postorder) {
      if(inorder.length != postorder.length) {
          return null;
      }
      
      Map<Integer, Integer> inorderMap = new HashMap<>();
      
      for(int i = 0; i < inorder.length; i++) {
          inorderMap.put(inorder[i], i);
      }
      
      TreeNode root = buildTree(postorder, 0, postorder.length - 1, inorderMap, 0, inorder.length - 1);
      return root;
  }
  
  public TreeNode buildTree(int[] postorder, int startPost, int endPost,
                   Map<Integer, Integer> inorderMap, int startIn, int endIn) {
      if(startPost > endPost || startIn > endIn) {
          return null;
      }
      
      TreeNode root = new TreeNode(postorder[endPost]);
      int rootIn = inorderMap.get(root.val);
      int rightNum = endIn - rootIn;
      
      root.right = buildTree(postorder, endPost - rightNum, endPost - 1, inorderMap, rootIn + 1, endIn);
      root.left = buildTree(postorder, startPost, endPost - rightNum - 1, inorderMap, startIn, rootIn - 1);
      
      return root;
  }
}