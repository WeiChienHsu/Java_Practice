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
  public List<List<Integer>> levelOrderBottom(TreeNode root) {
      List<List<Integer>> res = new ArrayList<List<Integer>>();
      
      if(root == null) return res;
      
      Deque<TreeNode> queue = new ArrayDeque<>();
      queue.offerLast(root);
      
      while(!queue.isEmpty()) {
          
          int size = queue.size();
          List<Integer> list = new ArrayList<>();
          
          for(int i = 0; i < size ; i++) {
              TreeNode cur = queue.pollFirst();
              list.add(cur.val);
              if(cur.left != null) queue.offerLast(cur.left);
              if(cur.right != null) queue.offerLast(cur.right);
          }            
          
          res.add(0, list);
      }
      return res;
  }
}