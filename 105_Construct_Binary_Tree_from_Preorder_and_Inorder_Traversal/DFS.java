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
  public TreeNode buildTree(int[] preorder, int[] inorder) {
      if(preorder.length != inorder.length) return null;    
  
  
      Map<Integer, Integer> inorderMap = new HashMap<>();
  
      for(int i = 0; i < inorder.length; i++) {
          inorderMap.put(inorder[i], i);
      }
  
      TreeNode root = buildTree(preorder, 0, preorder.length - 1, 0, inorder.length - 1, inorderMap);
          return root;
      }
  
  public TreeNode buildTree(int[] preorder, int preStart, int preEnd, 
                            int inStart, int inEnd, Map<Integer, Integer> inorderMap) {
      
      if(preStart > preEnd || inStart > inEnd) return null;
      
      // preorder = [3,9,20,15,7]
      // inorder = [9,3,15,20,7]
      
      TreeNode root = new TreeNode(preorder[preStart]); //[3]
      
      // find the index of root in inOrder
      // find leftNum in child tree
      int inRoot = inorderMap.get(preorder[preStart]);
      int leftNum = inRoot - inStart;
      
      root.left = buildTree(preorder, preStart + 1, preStart + leftNum, inStart, inRoot - 1, inorderMap); //[9]
      root.right = buildTree(preorder, preStart + leftNum + 1, preEnd, inRoot + 1, inEnd, inorderMap); //[20,15,7]
      
      return root;
      
  }
}