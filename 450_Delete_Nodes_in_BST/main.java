public class reorderList {

    public static void main(String[] args) {
        // head = 1 -> 2 -> 3 -> 4
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);


        // Deal with two LinkedList


        // Find middle and cut
        ListNode mid = findMiddle(head); // mid is a node in Head
        // Give a second head
        ListNode secondHead = mid.next;
        mid.next = null; //************

        // Reverse the second
        secondHead = reverse(secondHead);
        //Reconnect
//        head = merge(head, secondHead);

        ListNode current = head;

        while(current != null) {
            System.out.println(current.val);
            current = current.next;
        }

        }

    private static class ListNode<E> {
        E val;
        ListNode<E> next;
        ListNode<E> prev;
        ListNode(E x) { val = x; }
    }

    private static ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static ListNode reverse(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while(cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    private static ListNode merge(ListNode head, ListNode secondHead) {

        // cur =  1 -> 2 -> 3 - > 4 -> null
        // secondHead = 4 -> 3 -> null

        ListNode cur = head;

        while (secondHead != null) {
            ListNode tmp = secondHead.next;
            secondHead.next = cur.next;
            cur.next = secondHead;
            secondHead = tmp;
            cur = cur.next.next;
        }

        return head;
    }

        

}
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
        
        // mid: 3 -> 4 -> null
        // secondHead: 3 -> 4 -> null
        ListNode mid = findMid(head);
        ListNode secondHead = mid.next;
        mid.next = null;
        
        // head: 1 -> 2 -> null
        // secondHead: 4 -> 3 -> null
        secondHead = reverse(secondHead);
        // 1 -> 4 -> 2 -> 3 -> null
        head = merge(head, secondHead);
        
    }
    
    public ListNode findMid(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    public ListNode reverse(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while(cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public ListNode merge(ListNode head, ListNode secondHead){
        ListNode cur = head;
        
        while(secondHead != null) {
            ListNode tmp = secondHead.next;
            secondHead.next = cur.next;
            cur.next = secondHead;
            secondHead = tmp; ///
            cur = cur.next.next;////
        }
        return head; 
    }
    }