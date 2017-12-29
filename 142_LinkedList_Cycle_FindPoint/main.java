public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        ListNode start = head;
        
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                // when there is a circle
                while(slow != start) {
                    slow = slow.next;
                    start = start.next;
                }
                return start;
            }  
        }
        // There is no circle
        return null;
    }
}