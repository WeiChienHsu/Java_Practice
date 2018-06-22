/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
  public ListNode removeElements(ListNode head, int val) {
      
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      
      ListNode current = head;
      ListNode prev = dummy;
      
      while(current != null) {
          if(current.val == val) {
              prev.next = current.next;
          } else {
              prev = prev.next;
          }
          current = current.next;
      }
      return dummy.next;
  }
}