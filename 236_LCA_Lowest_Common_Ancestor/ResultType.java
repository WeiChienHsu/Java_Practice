class Solution {
    
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
      
      ResultType rt = helper(root, A, B);
      if(rt.a_exist && rt.b_exist) {
          return rt.lca;
      } else {
          return null;
      }
  }
  
  public ResultType helper(TreeNode root, TreeNode A, TreeNode B) {
      if(root == null) {
          return new ResultType(false, false, null);
      }
      
      ResultType left_rt = helper(root.left, A, B);
      ResultType right_rt = helper(root.right, A, B);
      
      boolean a_exist = left_rt.a_exist || right_rt.a_exist || root == A;
      boolean b_exist = left_rt.b_exist || right_rt.b_exist || root == B;
      
      if(root == A || root == B) {
          return new ResultType(a_exist, b_exist, root);
      }
      
      if(left_rt.lca != null && right_rt.lca != null) {
          return new ResultType(a_exist, b_exist, root);
      }
      
      if(left_rt.lca != null){
         return new ResultType(a_exist, b_exist, left_rt.lca);
      }
      
      if(right_rt.lca != null){
          return new ResultType(a_exist, b_exist, right_rt.lca);
      }
      
      return new ResultType(a_exist, b_exist, null);
  }
  
  class ResultType{
      public boolean a_exist, b_exist;
      public TreeNode lca;
      ResultType(boolean a, boolean b, TreeNode n) {
          a_exist = a;
          b_exist = b;
          lca = n;
      }
  }
  
}