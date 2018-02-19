class Solution {
  public List<Integer> rightSideView(TreeNode root) {
      List<Integer> res = new ArrayList<>();
      if(root == null) {
          return res;
      }
      
      Deque<TreeNode> queue = new ArrayDeque<>();
      queue.offerLast(root);
      
      while(!queue.isEmpty()){
          int size = queue.size();
          int lastVal = 0;
          for(int i = 0; i < size ; i++) {
              TreeNode cur = queue.pollFirst();
              if(cur.left != null){
                  queue.offerLast(cur.left);
              }
              
              if(cur.right != null) {
                  queue.offerLast(cur.right);
              }
              
              lastVal = cur.val;
          }
          res.add(lastVal);
      }
      return res;
      
      
  }
}