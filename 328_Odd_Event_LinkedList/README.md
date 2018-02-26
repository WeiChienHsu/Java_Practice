# Convert LinkedList to Odd-Event LinkedList
- Tow dummy nodes, dummy1 points to head, dummy2 points to head.next
- Add two pointers headOdd and headEven
- headOdd : conntect all Odd numbers, the last node connect to dummy2.next(headEven first)
- headEven : conntect all Even numbers, the last node connect to NULL

```
1 -> 2 -> 3 -> 4

dummy1 -> 1
dummy2 -> 2

headOdd : 1 -> 3
headEven: 2 -> 4 -> Null

headOdee -> dummy2.next(2->4->Null)

```

```java
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null) {
            return null;
        }
        
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        dummy1.next = head;
        dummy2.next = head.next;
        
        ListNode headOdd = head;
        ListNode headEven = head.next;
        
        while((headOdd != null && headOdd.next != null) && (headEven != null && headEven.next != null)) {
            headOdd.next = headOdd.next.next;
            headEven.next = headEven.next.next;
            headOdd = headOdd.next;
            headEven = headEven.next;
        }
        
        headOdd.next = dummy2.next;
        return dummy1.next;
    }
}
```