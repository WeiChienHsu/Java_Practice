/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) {
            return;
        } 
        
        // Find middle and cut
        ListNode mid = findMid(head);
        // Give a second head
        ListNode secondHead = mid.next;
        mid.next = null;
        // Reverse the second
        secondHead = reverse(secondHead);
        //Reconnect
        head = merge(head, secondHead);
    }
    
    private ListNode findMid(ListNode head) {
       // Fast and Slow pointer to find the middle Node 
        ListNode fast = head.next;
        ListNode slow = head;
        
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next; 
        }
        
        return slow;
            
    }
    
    private ListNode reverse(ListNode head) {
        if(head == null || head.next == null)
        return head;
        ListNode newHead = null;
        while(head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
        
    }
    
    private ListNode merge(ListNode head, ListNode secondHead) {
        //Dummy Node
        
        ListNode cur = head;
        
        while(secondHead != null ) {
            ListNode tmp = secondHead.next;
            secondHead.next = cur.next;
            cur.next = secondHead;
            secondHead = tmp;
            cur = cur.next.next;
        }
        return cur;        
    }
    
    
}