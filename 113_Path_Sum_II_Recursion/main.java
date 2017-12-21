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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>(); //Record Each result in the leaf
        
        if(root == null) {
            return res;
        }
        
        helper(root, sum, list, res);
        return res;
    }
    
    private void helper(TreeNode root, int sum, List<Integer> list, List<List<Integer>> res) {
        if (root == null) return;
        
        // Add the number we go through
        list.add(root.val);
        
        if( sum == root.val && root.right == null && root.left == null ) {
            res.add(new ArrayList<Integer>(list));
        } else {
            helper(root.left, sum - root.val, list, res);
            helper(root.right, sum - root.val, list, res); 
        }
        
        // Remove the node value from list since we'll back to last level
        list.remove(list.size() - 1);
    }
}