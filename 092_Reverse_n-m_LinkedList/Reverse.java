class Solution {
  public ListNode reverseBetween(ListNode head, int m, int n) {
      if(head == null){
          return null;
      }
      
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode pre = dummy;
      
      for(int i = 0; i < m - 1; i++) {
          pre = pre.next;
      }
      
      ListNode start = pre.next;
      ListNode then = start.next;
      
      for(int j = 0; j < n - m; j ++){
          start.next = then.next;
          then.next = pre.next;
          pre.next = then;
          then = start.next;
      }
      
      return dummy.next;
   }
}