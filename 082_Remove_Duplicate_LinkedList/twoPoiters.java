class Solution {
   
  public ListNode deleteDuplicates(ListNode head) {
      if(head == null) {
          return null;
      }
      
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode pre = dummy;
      ListNode cur = head;
      
      while(cur != null) {
          
          while(cur.next != null && cur.val == cur.next.val) {
              cur = cur.next;
          }
          
          if(pre.next != cur) {
              pre.next = cur.next;
          } else {
              pre = pre.next;
          }
          cur = cur.next;
          
      }
      return dummy.next;
  }
}