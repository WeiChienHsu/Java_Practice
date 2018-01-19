/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// Split and mergeSort

class Solution {
  public ListNode mergeKLists(ListNode[] lists) {
     if(lists == null || lists.length == 0) return null;
      return split(lists, 0, lists.length - 1);
  }
  
  private ListNode split(ListNode[] lists, int start, int end) {
      if(start == end) return lists[start];
      int mid = start + (end - start) / 2;
      ListNode head1 = split(lists, start, mid);
      ListNode head2 = split(lists, mid + 1, end);
      return mergeSort(head1, head2);
  }
  
  private ListNode mergeSort(ListNode head1, ListNode head2) {
      if(head1 == null || head2 == null) {
          return head1 == null ? head2 : head1;
      }
      if(head1.val <= head2.val) {
          head1.next = mergeSort(head1.next, head2);
          return head1;
      } else {
          head2.next = mergeSort(head1, head2.next);
          return head2;
      }
  }
}