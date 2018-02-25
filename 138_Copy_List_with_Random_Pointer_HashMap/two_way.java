/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
  public RandomListNode copyRandomList(RandomListNode head) {
      
      if(head == null) {
          return null;
      }
      
      Map<RandomListNode, RandomListNode> map = new HashMap<>();
      
      // Copy all nodes into Map
      RandomListNode cur = head;
      while(cur != null) {
          map.put(cur, new RandomListNode(cur.label));
          cur = cur.next;
      }
      
      RandomListNode dummy = new RandomListNode(0);
      dummy.next = map.get(head);
      
      while(head != null){
          map.get(head).next = map.get(head.next);
          map.get(head).random = map.get(head.random);
          head = head.next;
      }
      
      return dummy.next;
  }
}