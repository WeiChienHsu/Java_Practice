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
        TreeNode pre = dummy;
        TreeNode cur = root;
        
        // Find the node with target value
        while(cur!= null && cur.val != key) {
            pre = cur;
            if(cur.val < key) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        
        if(cur == null) return dummy.left; // Not found
        TreeNode target = cur;
        
        // Two Child - Find teh leftMost Node in right subTree
        
        if(cur.left != null && cur.right != null) {
            pre = cur;
            cur = cur.right;
            while(cur.left != null) {
                pre = cur;
                cur = cur.left;
            }
            target.val = cur.val;
        }
        
        // One or no Child
        if(cur.left == null) {
            if(pre.left == cur) {
                pre.left = cur.right;
            } else {
                pre.right = cur.right;
            }
        } else {
            if(pre.left == cur) {
                pre.left = cur.left;
            } else {
                pre.right = cur.left;
            }
        }
        return dummy.left;
    }
}