/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
  public void connect(TreeLinkNode root) {
      if(root == null) return;
      
      while(root != null) {
          TreeLinkNode tempChild = new TreeLinkNode(0);
          TreeLinkNode currentChild = tempChild;
          while(root != null) {
              if(root.left != null) {
                  currentChild.next = root.left;
                  currentChild = currentChild.next;
              }
              
              if(root.right != null) {
                  currentChild.next = root.right;
                  currentChild = currentChild.next;
              }
              
              root = root.next;
          }
          root = tempChild.next;
      }
  }
}