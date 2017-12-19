/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k <= 1) {
            return head;
        } 
        
        // Dummy node: Unify the first subproblem with all others
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // pre.next points to head of sublist to be reversed
        ListNode pre = dummy;
        int count = k;
        
        while (head != null) {
            count--; // Move untial count = 0
            // Reverse every K steps
            if(count == 0) {
                pre = reverse(pre, head.next);
                head = pre.next;
                count = k;
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }
    
    private ListNode reverse(ListNode preHead, ListNode nextHead) {
        ListNode tail = preHead.next;
        ListNode cur = tail.next;
        while (cur != nextHead) {
            ListNode tmp = cur.next;
            cur.next = preHead.next;
            preHead.next = cur;
            cur = tmp;
        }
        tail.next = nextHead;
        return tail;
    }
}