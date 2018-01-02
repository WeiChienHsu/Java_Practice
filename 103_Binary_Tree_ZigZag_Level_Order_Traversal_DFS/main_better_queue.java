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
        
 
        Deque<TreeNode> queue = new ArrayDeque<>();
        
        queue.offerLast(root);
        
        int size = queue.size();
        boolean order = true;
        
        while(!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            
            for(int i = 0; i < size ; i++) {
                // poll out the node and add in the temp list
                TreeNode cur = queue.pollFirst();
                if(order) {
                    list.add(cur.val);
                } else {
                    list.add(0, cur.val);
                }
                if(cur.left != null) queue.offerLast(cur.left);
                if(cur.right != null) queue.offerLast(cur.right);
            }
            
            res.add(list);
            size = queue.size();
            order = !order;
        }
        return res;
    }
}