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
  public int maxDepth(Node root) {
      if(root == null) return 0;
      int len = 0;
      Deque<Node> queue = new ArrayDeque<>();
      queue.offerLast(root);
      
      while(!queue.isEmpty()) {
          len++;
          int size = queue.size();
          for(int i = 0; i < size; i++) {
              Node currentNode = queue.pollFirst();
              List<Node> childrenList = currentNode.children;
              for(int j = 0; j < childrenList.size(); j++) {
                  queue.offerLast(childrenList.get(j));
              }
          }

      }
      
      return len;
      
  }
}