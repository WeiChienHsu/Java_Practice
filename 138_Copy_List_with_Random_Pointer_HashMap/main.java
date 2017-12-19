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
            return head;
        }
        
        // Map for saving old nodes and pointer
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cur = head;
        
        while (cur != null) {
            if(!map.containsKey(cur)) {
                RandomListNode curCopy = new RandomListNode(cur.label);
                map.put(cur, curCopy);
            }
            
            if(cur.next != null && !map.containsKey(cur.next)) {
                RandomListNode nextCopy = new RandomListNode(cur.next.label);
                map.put(cur.next, nextCopy);
            }
            
            if(cur.random != null && !map.containsKey(cur.random)) {
                RandomListNode randomCopy = new RandomListNode(cur.random.label);
                map.put(cur.random, randomCopy);
            }
            
        // Connect curCopy with nextCopy and randomCopy
            if (cur.next != null){
                // curCopy.next = nextCopy
                map.get(cur).next = map.get(cur.next);
            }
                // curCopy.random = randomCopy
            if(cur.random != null) {
                map.get(cur).random = map.get(cur.random);
            }
            // Next round
            cur = cur.next;
        }
        return map.get(head);
        
    }
}