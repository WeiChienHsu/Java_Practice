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
    public List<List<Integer>> levelOrder(TreeNode root) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null) return result;
        
        Deque<TreeNode> queue = new ArrayDeque<>();
        
        queue.add(root); // add the first level
        
        // pop out the first level to record in a List and add it's children in the same time
        while (!queue.isEmpty()) {
            // Record the size for knowing size of next level
            int size = queue.size();
            // Create a List to record numbers in each level and add in a result List
            List<Integer> list = new ArrayList<>();
            
            for(int i = 0; i < size; i++) {
                TreeNode current = queue.pollFirst();
                list.add(current.val);
                // Check if those are null, and add the children nodes of previous root
                if(current.left != null) queue.add(current.left);
                if(current.right != null) queue.add(current.right);
            }
            result.add(list);
        }
        return result;
    }
}