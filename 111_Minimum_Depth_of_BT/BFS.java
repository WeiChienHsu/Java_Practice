class Solution {
  public int minDepth(TreeNode root) {
      if(root == null) return 0;
      Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
      int curLevel = 1;
      queue.offerLast(root);
      
      while(!queue.isEmpty()) {
          int size = queue.size();
          for(int i = 0; i < size ; i++) {
              TreeNode cur = queue.pollFirst();
              
              if(cur.left == null && cur.right == null) return curLevel;
              
              if(cur.left != null) {
                  queue.offerLast(cur.left);
              }
              
              if(cur.right != null) {
                  queue.offerLast(cur.right);
              } 
          }
          curLevel++;
      }
      
      return curLevel;
  }
}