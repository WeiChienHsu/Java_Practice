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
        if(head == null || head.next == null) {
            return head;
        }
        
        ListNode smallDummy = new ListNode(0);
        ListNode largeDummy = new ListNode(0);
        
        ListNode largeCur = largeDummy;
        ListNode smallCur = smallDummy;
        
        while (head != null) {
            if(head.val < x) {
                smallCur.next = head;
                smallCur = head;
            } else {
                largeCur.next = head;
                largeCur = head;
            }
            
            head = head.next;
        }
        
        smallCur.next = largeDummy.next;
        largeCur.next = null;
        
        return smallDummy.next;   
    }
    
}