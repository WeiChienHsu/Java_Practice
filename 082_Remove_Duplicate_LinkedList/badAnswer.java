class Solution {
  public int duplVal = 121231;
  public ListNode deleteDuplicates(ListNode head) {
      if(head == null) {
          return null;
      }
      
      ListNode dummy = new ListNode(Integer.MIN_VALUE);
      dummy.next = head;
      ListNode pre = dummy;
      
      while(head != null && head.next != null ) {
          if(head.val == head.next.val) {
              duplVal = head.val;
              pre.next = head.next.next;
              head = pre.next;
          } else if(head.val == duplVal) {
              pre.next = head.next;
              head = pre.next;
          }
          else {
              pre = pre.next;
              head = head.next;
          }
      }
      
      if(head != null && head.val == duplVal) {
          pre.next = head.next;
      }
      
      return dummy.next;
  }
}