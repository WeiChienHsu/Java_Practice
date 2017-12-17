# Reverse Node

## Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.


```
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
```

### How to Reverse LinkedList
```
null   1  ->  2  ->  3  ->  4
  p   cur
      tmp

while cur != null {
tmp = cur.next;
cur.next = pre;
pre = cur;
cur = tmp
} 

null <-  1   2  ->  3  ->  4
         p  cur
            tmp
```

- Separate each K number : we need two pointers
* Head: Track the number next we need to connect wiht
* Tail: Deal with the reverse
```java
    ListNode pre = dummy;
    
    ListNode tail = preHead.next;
    ListNode cur = tail.next;
```
- Separate each K counts
```java
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
```
- Used Dummy node to Unify the first subproblem with all others, and in the end, return the dummy.next

```java
ListNode dummy = new ListNode(0);
        dummy.next = head;
        // pre.next points to head of sublist to be reversed
        ListNode pre = dummy;
        int count = k;

return dummy.next;
```

- Reverse the sub-problems (Need to add a tail to save the next head)
```java
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
```
