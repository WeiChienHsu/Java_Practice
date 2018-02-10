public boolean isValidBST(TreeNode root) {
  List<Integer> nodeList = new ArrayList<Integer>();
  
  inorder(root, nodeList);
  System.out.println(nodeList);
  for(int i = 0; i < nodeList.size() - 1; i++) {
      if(nodeList.get(i) >= nodeList.get(i+1)) {
          return false;
      }
  }
  return true;
  
}

public void inorder(TreeNode root, List<Integer> nodeList) {
  if(root == null){
      return;
  }
  
  inorder(root.left, nodeList);
  nodeList.add(root.val);
  inorder(root.right, nodeList);
}