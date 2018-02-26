# Remove Duplicate LinkedList

```
0 -> 1 -> 1 -> 3
p    h

```


```java
        if(head == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(Integer.MAX_VALUE);
        dummy.next = head;
        ListNode pre = dummy;
        
        while(head != null) {
            if(pre.val == head.val) {
                pre.next = pre.next.next;
                head = pre.next;
            } else {
                pre = pre.next;
                head = head.next;
            }
        }
```