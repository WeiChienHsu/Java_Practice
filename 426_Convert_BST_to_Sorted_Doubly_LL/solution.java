/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

class Solution {
  Node prev = null;

  public Node treeToDoublyList(Node root) {
      if(root == null) return root;
      
      Node head = new Node(0, null, null);
      prev = head;

      /* Rebuil the root */
      helper(root);
      
      /* Connect the first element with the tail */
      head.right.left = prev;

      /* Connect the tail with first element */
      prev.right = head.right;
      
      return head.right;
  }
  /* Inorder traversal to get the right order for LL */
  public void helper(Node current) {
      
      if(current == null) return;  
      
      helper(current.left);
      
      prev.right = current;
      current.left = prev;
      prev = current;
      
      helper(current.right);
  }
}