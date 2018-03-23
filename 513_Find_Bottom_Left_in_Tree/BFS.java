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
  public int findBottomLeftValue(TreeNode root) {
      int[] res = new int[1];
      Deque<TreeNode> queue = new ArrayDeque<>();
      queue.offerLast(root);
      
      while(!queue.isEmpty()) {
          int size = queue.size();
          boolean isFirst = true;
          for(int i = 0; i < size; i++) {
              TreeNode cur = queue.pollFirst();
              if(cur.left != null) queue.offerLast(cur.left);
              if(cur.right != null) queue.offerLast(cur.right);
              if(isFirst) {
                  res[0] = cur.val;
                  isFirst = false;
              }
          }
      }
      return res[0];
  }
}