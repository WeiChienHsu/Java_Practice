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
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        
        Deque <TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;
        
        while(cur != null || !stack.isEmpty()) {
            
            // go Left for all nodes
            
            if(cur != null) {
                stack.offerLast(cur);
                cur = cur.left;
            } else { // When left side meet the end poll out the node to compare with last node and go right
                cur = stack.pollLast();
                if (pre != null && cur.val <= pre.val) return false;
                pre = cur;
                cur = cur.right;
            }
        }
        
        return true;
    }
}