
class Solution {
  public TreeNode sortedListToBST(ListNode head) {
      if(head == null){
          return null;
      }
      
      ListNode tail = null;
      return buildTree(head, tail);
  }
  
  private TreeNode buildTree(ListNode head, ListNode tail) {
      if(head == tail) {
          return null;
      }
      
      ListNode slow = head;
      ListNode fast = head;
      
      while(fast != tail && fast.next != tail){
          fast = fast.next.next;
          slow = slow.next;
      }
      
      
      TreeNode root = new TreeNode(slow.val);
      root.left = buildTree(head, slow);
      root.right = buildTree(slow.next, tail);
      return root;
      
  }
  
}