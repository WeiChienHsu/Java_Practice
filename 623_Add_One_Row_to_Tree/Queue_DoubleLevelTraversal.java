/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
  public TreeNode addOneRow(TreeNode root, int v, int d) {
      // Find the d-1 Level and Put those Nodes in that level into the Queue.
      // Poll Node out from queue one by one, Create a new Node with Value v, 
      // Connect newNode with the original Node's left / right child and Connect original node with the newNode.
  
      // Deal with the corner case
      // when the d == 1 could not find d - 1 as a root
      if(d == 1) {
          TreeNode newRoot = new TreeNode(v);
          newRoot.left = root;
          return newRoot;
      }
      
      Deque<TreeNode> queue = new ArrayDeque<>();
      queue.offerLast(root);
      int depth = 1;
      
      while(!queue.isEmpty()) {
          // Find the d-1 Level and put them into Queue
          if(depth != d - 1) {
              int size = queue.size();
              // Same Level Traversal
              for(int i = 0; i < size; i++) {
                  TreeNode node = queue.pollFirst();
                  if(node.left != null) queue.offerLast(node.left);
                  if(node.right != null) queue.offerLast(node.right);
              }
              // Deal with next level
              depth++;
          } else {
              // Add the additional Nodes into current Level
              // Connect curent children with newNode
              int size = queue.size();
              for(int i = 0; i < size; i++) {
                  TreeNode currentNode = queue.pollFirst();
                  
                  // Create New Left Node
                  TreeNode newLeft = new TreeNode(v);
                  newLeft.left = currentNode.left;
                  currentNode.left = newLeft;
                  
                  // Create New Right Node
                  TreeNode newRight = new TreeNode(v);
                  newRight.right = currentNode.right;
                  currentNode.right = newRight; 
              }
              
              break;
          }
      }
      return root;
  }
}