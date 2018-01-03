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
    int maxValue;
    
    public int maxPathSum(TreeNode root) {
        maxValue =  Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }
    
    private int maxPathDown(TreeNode root) {
        if(root == null) return 0;
        int left = Math.max(0, maxPathDown(root.left)); // compare with the return value
        int right = Math.max(0, maxPathDown(root.right)); // 0 means when the node.val < 0
        maxValue = Math.max(maxValue, left + right + root.val);
        return Math.max(left, right) + root.val;
    }
}