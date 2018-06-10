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

class Solution {
    public boolean isSymmetric(TreeNode root) {
        
        return root == null || Solution.helper(root.left, root.right);
    }
    
    public static boolean helper(TreeNode rootL, TreeNode rootR) {
        if(rootL == null || rootR == null) return rootL == rootR;
        if(rootL.val != rootR.val) return false;
        
        // 1. 找出第一規律， something.left.left == something.right.right
        // 2. 第二規律， something.left.right = something.right.left
        return Solution.helper(rootL.left, rootR.right) && Solution.helper(rootL.right, rootR.left);
    }
}