/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// - Used minHeap : store every Head in the Heap
// - Set up a Dummy node and point to the peek
// - prev pointer and cur pointer
// - pop out the smallest and use d
// - When you pop out the Point, add it's next point into Heap

class Solution {
  public ListNode mergeKLists(ListNode[] lists) {
      PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.length, 
          new Comparator<ListNode>(){
              @Override
              public int compare(ListNode o1, ListNode o2) {
                  if(o1.val == o2.val) {
                     return 0;
                   } else {
                       return o1.val - o2.val;
                   }
               }
           });
      
      for(ListNode n : lists) {
          if(n != null) {
              pq.add(n);
          }
      }
      
      ListNode dummy = new ListNode(0);
      ListNode prev = dummy;
      
      while(!pq.isEmpty()) {
          ListNode cur = pq.poll();
          if(cur.next != null) {
              pq.add(cur.next);
          }
          prev.next = cur;
          prev = prev.next;
      }
      return dummy.next;
  }
}