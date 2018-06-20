/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
  public ListNode sortList(ListNode head) {
      if(head == null || head.next == null) return head;
      
      // 1. Find the Middle Point
      ListNode slow = head, fast = head, prev = null;
      
      while(fast != null && fast.next != null) {
          prev = slow;
          slow = slow.next;
          fast = fast.next.next;
      }
      
      prev.next = null;
      
      ListNode left = sortList(head);
      ListNode right = sortList(slow);

      
      return Solution.doMerge(left, right);
  }
  
  public static ListNode doMerge(ListNode left, ListNode right) {
      ListNode dummy = new ListNode(0);
      ListNode current = dummy;
      
      while(left != null && right != null) {
          if(left.val < right.val) {
              current.next = left;
              left = left.next;
          } else {
              current.next = right;
              right = right.next;
          }
          
          current = current.next;
      }
      
      if(left != null) {
          current.next = left;
      }
      
      if(right != null) {
          current.next = right;
      }
      
      return dummy.next; 
  }
  
}