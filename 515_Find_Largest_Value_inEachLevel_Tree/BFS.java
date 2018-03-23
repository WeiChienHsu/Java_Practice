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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            int maxNum = Integer.MIN_VALUE;
            for(int i = 0; i < size; i++) {
                TreeNode cur = queue.pollFirst();
                if(cur.left != null) queue.offerLast(cur.left);
                if(cur.right != null) queue.offerLast(cur.right);
                if(cur.val > maxNum) {
                    maxNum = cur.val;
                }
            }
            res.add(maxNum);
        }
        return res;
    }
}