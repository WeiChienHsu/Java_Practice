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
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return helper(root, set, k);
    }
    
    public boolean helper(TreeNode root, Set set, int target) {
        if(root == null) return false;
        if(set.contains(target - root.val)) return true;
        set.add(root.val);
        return helper(root.right, set, target) || helper(root.left, set, target);  
    } 
}