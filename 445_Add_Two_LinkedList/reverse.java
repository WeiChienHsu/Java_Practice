class Solution {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
      ListNode _reverse_l1 = reverseLinkedList(l1);
      ListNode _reverse_l2 = reverseLinkedList(l2);
      ListNode dummy = new ListNode(0);
      ListNode cur = dummy;
      boolean carry = false;
      
      while(_reverse_l1 != null || _reverse_l2 != null || carry) {
          int num1 = _reverse_l1 != null? _reverse_l1.val : 0;
          int num2 = _reverse_l2 != null? _reverse_l2.val : 0;
          int sum = carry? num1 + num2 + 1 : num1 + num2;
          carry = false;
          if(sum > 9) {
              sum -= 10;
              carry = true;
          }
          cur.next = new ListNode(sum);
          cur = cur.next;
          if(_reverse_l1 != null) _reverse_l1 = _reverse_l1.next;
          if(_reverse_l2 != null) _reverse_l2 = _reverse_l2.next;
      }
      
      return reverseLinkedList(dummy.next);
  }
  

  
  public static ListNode reverseLinkedList(ListNode root){
      ListNode pre = null;
      ListNode cur = root;
      while(cur != null) {
          ListNode next = cur.next;
          cur.next = pre;
          pre = cur;
          cur = next;
      }
      
      return pre;     
  }
}