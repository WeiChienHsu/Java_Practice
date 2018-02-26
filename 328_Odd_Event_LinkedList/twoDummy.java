class Solution {
  public ListNode oddEvenList(ListNode head) {
      if(head == null) {
          return null;
      }
      
      ListNode dummy1 = new ListNode(0);
      ListNode dummy2 = new ListNode(0);
      dummy1.next = head;
      dummy2.next = head.next;
      
      ListNode headOdd = head;
      ListNode headEven = head.next;
      
      while((headOdd != null && headOdd.next != null) && (headEven != null && headEven.next != null)) {
          headOdd.next = headOdd.next.next;
          headEven.next = headEven.next.next;
          headOdd = headOdd.next;
          headEven = headEven.next;
      }
      
      headOdd.next = dummy2.next;
      return dummy1.next;
  }
}