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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null) return res;
        // Deque maintain the current node in the same level
        Deque<TreeNode> deque = new ArrayDeque<TreeNode>();
        boolean isLeftToRight = true;
        deque.addFirst(root);
        
        while(!deque.isEmpty()) {
            int size = deque.size(); // Check the level
            List<Integer> list = new ArrayList<Integer>(); // List to record the result
            for (int i = 0; i < size; i++) {
                if(isLeftToRight) {
                    TreeNode current = deque.pollFirst();
                    list.add(current.val);
                    // First Left next Right
                    if(current.left != null) {
                        deque.addLast(current.left);
                    } 
                    
                    if(current.right != null) {
                        deque.addLast(current.right);
                    }
                } else {
                    TreeNode current = deque.pollLast();
                    list.add(current.val);
                    if(current.right != null) {
                        deque.addFirst(current.right);
                    }
                    if(current.left != null) {
                        deque.addFirst(current.left);
                    }
                }
            }
            
            isLeftToRight = !isLeftToRight;
            res.add(list);
        }
        return res;
    }
}