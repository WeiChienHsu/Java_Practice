class Solution {
  public ListNode rotateRight(ListNode head, int k) {
      if(head == null) return head;
      if(head.next == null) return head;
      
      // Situtaion that k is too long (ex. 200000)
      // We need to avoid the repeated rotation
      int length = 0;
  ListNode node = head;
  while(node != null){
    node = node.next;
    length++;
  }
  k = k % length;

      
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      while(k > 0) {
          dummy.next = rotate(dummy.next);
          k--;
      }
      return dummy.next;
  }
  
  public static ListNode rotate(ListNode head) {
      ListNode cur = head;
      // Find the Last - 1 one to conect with the null
      while(cur.next.next != null) {
          cur = cur.next;
      }
      ListNode last = cur.next;
      cur.next = null;
      last.next = head;
      
      // Return the new head Node replaced by the Last node
      return last;
  }
}