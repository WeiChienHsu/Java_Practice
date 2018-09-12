/*  TEST Case
{"$id":"1","children":[
  {"$id":"2","children":[
    {"$id":"5","children":[],"val":5},
    {"$id":"6","children":[],"val":6}],"val":3},
    {"$id":"3","children":[],"val":2},
    {"$id":"4","children":[],"val":4}],"val":1}


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
  public List<Integer> postorder(Node root) {
      List<Integer> result = new ArrayList<>();
      helper(root, result);
      return result;
  }
  
  public void helper(Node root, List<Integer> list) {
      if(root == null) return;
      List<Node> childrenList = root.children;
      
      for(int i = 0; i < childrenList.size(); i++) {
          helper(childrenList.get(i), list);
      }
      
      list.add(root.val);
  }
}