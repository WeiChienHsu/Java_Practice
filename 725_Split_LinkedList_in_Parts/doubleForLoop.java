/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
  public ListNode[] splitListToParts(ListNode root, int k) {
      ListNode[] parts = new ListNode[k];
      
      int len = 0;
      for (ListNode node = root; node != null; node = node.next)
          len++;
      
      int n = len / k, r = len % k; 
      // n : minimum guaranteed part size; 
      // r : extra nodes spread to the first r parts;
      
      ListNode cur = root, prev = null;
      
      for (int i = 0; cur != null && i < k; i++, r--) {
          parts[i] = cur;
          for (int j = 0; j < n + (r > 0 ? 1 : 0); j++) {
              prev = cur;
              cur = cur.next;
          }
          prev.next = null;
      }
      return parts;        
  }
}
