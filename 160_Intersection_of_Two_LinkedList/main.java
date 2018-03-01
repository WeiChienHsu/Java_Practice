class Solution {
  public ListNode partition(ListNode head, int x) {
      ListNode small = new ListNode(0); // Less than x
      ListNode large = new ListNode(0); // More and Equal to x
      ListNode dummy1 = small; 
      ListNode dummy2 = large;
      
      while(head != null) {
          if(head.val < x) {
              small.next = head;
              small = small.next;
          } else {
              large.next = head;
              large = large.next;
          }
          head = head.next;
      }
      
      small.next = dummy2.next;
      large.next = null;
      
      
      return dummy1.next;
  }
}