/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
  public int numComponents(ListNode head, int[] G) {
      Set<Integer> set = new HashSet<>();
      for(int i = 0; i < G.length; i++) {
          set.add(G[i]);
      }
      
      ListNode cur = head;
      int count = 0;
      while(cur != null) {
          if(set.contains(cur.val)) {
              count++;
              while(cur.next != null && set.contains(cur.next.val)) {
                  cur = cur.next;
              }   
          }
          cur = cur.next;     
      }
      return count;
  }
}