/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
  public int max = 0;
  
  public int maxDepth(Node root) {
      dfsHelper(root, 0);
      return max;
  }
  
  public void dfsHelper(Node root, int len) {
      if(root == null) return;
      len++;
      for(int i = 0; i < root.children.size(); i++) {
          dfsHelper(root.children.get(i), len);
      }
      
      max = Math.max(max, len);
      
      len--;
  }
}