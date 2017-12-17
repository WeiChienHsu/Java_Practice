# 19 Remove n th Node from the end of List

## Given a linked list, remove the nth node from the end of list and return its head.

```
   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
```
## Solution - Used Fast / Slow pointers

### Remove the Last N

```
 D  ->  O ->  O ->  O ->  O -> null
 sf

fast move N

 D  ->  O ->  O ->  O ->  O -> null
 s            f

slow/fast move together

 D  ->  O ->  O ->  O ->  O -> null
              s           f
        
When f.next = null, change the s.next to s.next.next

D  ->  O ->  O  ->->->-> O -> null
             s      0

```

- Since if we need to remove the last Nth point, we need to change the last (N+1) point.next to the point.next.next
```java
slow.next = slow.next.next;
return dummy.next;
```
- Always use a dummy Node if we need to add or remove Node from LinkList
```java
ListNode dummy = new ListNode(0);
ListNode slow = dummy;
ListNode fast = dummy;
dummy.next = head;
```
- Let the fast and slow pointers have a same distance, then move the same path. If fast.next = null which means it's on the end, the position of slow pointer is the last (N+1) Node.
```java
 // Fast pointer Move n step
while(n > 0) {
    fast = fast.next;
    n --;
}
while(fast.next != null) {
    fast = fast.next;
    slow = slow.next;
}
```

