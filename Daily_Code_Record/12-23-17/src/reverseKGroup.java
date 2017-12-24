public class reverseKGroup {

    private static class ListNode<E> {
        E val;
        ListNode<E> next;
        ListNode<E> prev;
        ListNode(E x) { val = x; }
    }

    public static void main(String[] args) {
        // head = 1 -> 2 -> 3 -> 4
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode current =  reverseK(head, 2);
        while(current != null){
            System.out.println(current.val);
            current = current.next;
        }


    }

    public static ListNode reverseK(ListNode head, int k){

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        int count = k;

        while(head != null) {
            count--;
            if(count == 0) {
                pre = reverse(pre, head.next); // *dummy -> 1 -> 2 | *3 -> 4 |
                head = pre.next;
                count = k;
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }

    public static ListNode reverse(ListNode preHead, ListNode nextHead) {
        ListNode tail = preHead.next;
        ListNode cur = tail.next;
        while(cur != nextHead) {
            ListNode tmp = cur.next; //3
            cur.next = preHead.next; //1
            preHead.next = cur; // 2
            cur = tmp; // 3
        }
        tail.next = nextHead;
        return tail;
    }







    }
