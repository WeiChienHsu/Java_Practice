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
    Map<Integer, Integer> map;
    int max = 0;
public int[] findMode(TreeNode root) {
    this.map = new HashMap<>();
    List<Integer> list = new ArrayList<>();
    
    if (root == null) return new int[0];
    
    inorder(root);
    
    for(int key : map.keySet()){
        if(map.get(key) == max) list.add(key);
    }
    
    int[] res = new int[list.size()];
    
    for(int i = 0; i < res.length ; i++) {
        res[i] = list.get(i);
    }
    
    return res;
}

public void inorder(TreeNode root) {
    if(root == null) return;
    if(root.left != null) inorder(root.left);
    map.put(root.val, map.getOrDefault(root.val, 0) + 1);
    max = Math.max(max, map.get(root.val));
    if(root.right != null) inorder(root.right);
}
}