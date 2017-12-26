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
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isMinor(root.right, root.left);
           
    }
    
    public boolean isMinor(TreeNode right, TreeNode left) {
        if(right == null && left == null) return true;
        if(right == null || left == null) return false;
        return (right.val == left.val) && isMinor(left.right, right.left) && isMinor(right.right, left.left);
    }
   
}