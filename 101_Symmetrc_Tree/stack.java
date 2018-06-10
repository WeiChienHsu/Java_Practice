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
        if(root.left == null || root.right == null) return root.left == root.right;
        
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root.left);
        stack.offerFirst(root.right);
        
        while(!stack.isEmpty()) {
            if(stack.size() % 2 != 0) return false; // Have different numbers of Nodes in subtree
            TreeNode right = stack.pollFirst();
            TreeNode left = stack.pollFirst();
            if (right.val != left.val) return false;
            
            // Check left.left and right.right
            if(left.left != null) {
                if(right.right == null) return false;
                // Push left.left and right.tight into Stack
                stack.offerFirst(left.left);
                stack.offerFirst(right.right);
            }
            
            if(left.left == null && right.right != null) return false;
            
            // Check left.right and right.left
            if(left.right != null) {
                if(right.left == null) return false;
                // Push left.right and right.left into Satck
                stack.offerFirst(left.right);
                stack.offerFirst(right.left);
            }
            
            if(left.right == null && right.left != null) return false;
            
        }
        
        return true;
    }

}