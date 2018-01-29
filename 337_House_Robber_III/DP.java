class Solution {
  public int rob(TreeNode root) {
      if (root == null) return 0;
      Map<TreeNode, Integer> map = new HashMap<>();
      return robSub(root, map);
  }
  
  public int robSub(TreeNode root, Map<TreeNode, Integer> map) {
      if(root == null) return 0;
      if(map.containsKey(root)) return map.get(root);
      
      int val = 0;
      
      if(root.left != null) val += robSub(root.left.left, map) + robSub(root.left.right, map);
      if(root.right != null) val += robSub(root.right.left, map) + robSub(root.right.right, map);
      
      val = Math.max(root.val + val, robSub(root.left, map) +robSub(root.right, map));
      map.put(root, val);
      return val;
  }
}