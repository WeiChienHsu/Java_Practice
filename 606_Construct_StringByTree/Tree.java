public String tree2str(TreeNode t) {
  StringBuilder sb = new StringBuilder();
  helper(sb,t);
  return sb.toString();
}
public void helper(StringBuilder sb,TreeNode t){
  if(t!=null){
      sb.append(t.val);
      if(t.left!=null||t.right!=null){
          sb.append("(");
          helper(sb,t.left);
          sb.append(")");
          if(t.right!=null){
              sb.append("(");
              helper(sb,t.right);
              sb.append(")");
          }
      }
  }
}