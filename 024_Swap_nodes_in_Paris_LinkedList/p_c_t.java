class Solution {
  public ListNode swapPairs(ListNode head) {
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode pre = dummy;
      
      while(pre != null && pre.next != null && pre.next.next != null) {
          ListNode cur = pre.next;
          ListNode then = pre.next.next;
          
          pre.next = then;
          cur.next = then.next;
          then.next = cur;
          pre = cur;
      }
      
      return dummy.next;
  }
}