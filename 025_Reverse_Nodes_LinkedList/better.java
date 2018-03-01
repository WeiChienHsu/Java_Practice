/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


// O ->  1 ->  2  ->  3  ->  4  ->  5
// pre ->  n1  ->  n2  ->  .. -> nk-1 -> nk -> nk+1
// pre ->  nk  ->  nk-1 -> .. -> n2 -> n1 -> nk+1


class Solution {
  public ListNode reverseKGroup(ListNode head, int k) {
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode pre = dummy;
      while(pre != null) {
          pre = reverseNextKNodes(pre, k);
      }
      return dummy.next;
  }
  private ListNode reverseNextKNodes(ListNode head, int k) {
      ListNode n1 = head.next;
      ListNode cur = head;
      for(int i = 0; i < k; i++) {
          cur = cur.next;
          if(cur == null) {
              return null;
          }
      }
      ListNode nk = cur;
      ListNode nkPlusOne = cur.next;
      
      // Reverse
      ListNode pre = head;
      cur = head.next;
      while(cur != nkPlusOne) {
          ListNode temp = cur.next;
          cur.next = pre;
          pre = cur;
          cur = temp;
      }
      
      head.next = nk;
      n1.next = nkPlusOne;
      
      return n1;
  }
}