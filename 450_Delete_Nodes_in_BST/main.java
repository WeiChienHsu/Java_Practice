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
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return root;
        TreeNode dummy = new TreeNode(Integer.MIN_VALUE);
        dummy.left = root;
        TreeNode prev = dummy;
        TreeNode cur = root;
            
            // Find the Node with key
        while(cur != null && cur.val != key) {
            prev = cur;
            if(cur.val < key) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        // No found
        if(cur == null) return dummy.left;
        
            // Give a parent Pointer
        TreeNode target = cur;
        // Case 1: Two Children
        if(cur.left != null && cur.right != null) {
            // Find the leftMost node in right subTree
            prev = cur;
            cur = cur.left;
            while(cur.right != null) {
                prev = cur;
                cur = cur.right;
            }
            // Change value - 
            target.val = cur.val;
        }
        
        // Case 2: Delete cur, which has one or no child
        if(cur.left == null) {
            if(prev.left == cur) {
                prev.left = cur.right;
            } else {
                prev.right = cur.right;
            }
        } else {
            if(prev.left == cur) {
                prev.left = cur.left;
            } else {
                prev.right = cur.left;
            }
        }
        return dummy.left;
    }
}