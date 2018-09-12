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
  public List<List<Integer>> levelOrder(Node root) {
      List<List<Integer>> result = new ArrayList<>();
      if(root == null) return result;
      
      Deque<Node> queue = new ArrayDeque<>();
      queue.offerLast(root);
      
      while(!queue.isEmpty()) {
          /* Do a Level Traversal */
          int size = queue.size();
          List<Integer> list = new ArrayList<>();
          
          for(int i = 0; i < size; i++) {
              Node currentNode = queue.pollFirst();
              list.add(currentNode.val);
              /* Traverse through current node's all children */
              List<Node> childrenList = currentNode.children;
              for(int j = 0; j < childrenList.size(); j++) {
                  /* Add the child into temporary list */
                  if(childrenList.get(j) != null) {
                      queue.offerLast(childrenList.get(j));
                  }
              }
          }
          /* Gather all results in the same level and insert in the first positoin of the result list */
          result.add(list);
      }
      
      return result;
  }
}