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
    // Find the height of Left subtree
    public int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }
    
    public int countNodes(TreeNode root) {
        int h = height(root);
        return h < 0 ? 0 :
            // check if the left subtree is full with left and right nodes
            height(root.right) == h-1 ? (1 << h) + countNodes(root.right) // root.right.legt have node
                                      : (1 << h - 1) + countNodes(root.left);
    }
}