/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {
    public ListNode partition(ListNode head, int x) {
        Deque<Integer> deque = new ArrayDeque<>();
        while(head != null) {
            deque.offerLast(head.val);
            head = head.next;
        }
        
        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);  //dummy heads of the 1st and 2nd deque
        ListNode curr1 = dummy1;
        ListNode curr2 = dummy2;      //current tails of the two deque;

        
        while(!deque.isEmpty()) {
            int curVal = deque.pollFirst();
            if(curVal >= x) {
                curr2.next = new ListNode(curVal);
                curr2 = curr2.next;
            } else {
                curr1.next = new ListNode(curVal);
                curr1 = curr1.next;
            }
        }
        
        curr2.next = null;          //important! avoid cycle in linked list. otherwise u will get TLE.
        curr1.next = dummy2.next;
        return dummy1.next;
    }    
}