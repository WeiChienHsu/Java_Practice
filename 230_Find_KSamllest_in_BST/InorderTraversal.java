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
  public int kthSmallest(TreeNode root, int k) {
      if(root == null) return -1;
      List<Integer> nodeLists = new ArrayList<>();
      Solution.inOrderTraversal(root, nodeLists);
      
      return nodeLists.get(k-1);
  }
  
  public static void inOrderTraversal(TreeNode root, List<Integer> nodeLists) {
      if(root == null) return;
      
      inOrderTraversal(root.left, nodeLists);
      nodeLists.add(root.val);
      inOrderTraversal(root.right, nodeLists);
  }
}