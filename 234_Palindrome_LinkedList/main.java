class Solution {
  public boolean isPalindrome(ListNode head) {
      
      // Find Middle Node
      ListNode fast = head;
      ListNode slow = head;
      while(fast != null && fast.next != null) {
          slow = slow.next;
          fast = fast.next.next;
      }
      
      // Reverse Slow Pointer
      slow = reverse(slow);
      fast = head;
          
      // Reassign head to first and Compare with slow
      while(slow != null) {
          if(slow.val != fast.val) {
              return false;
          }
          
          slow = slow.next;
          fast = fast.next;
      }
      return true;
  
  }
  private ListNode reverse(ListNode cur) {
      ListNode pre = null;
      while(cur != null) {
          ListNode temp = cur.next;
          cur.next = pre;
          pre = cur;
          cur = temp;
      }
      return pre;
  }
}