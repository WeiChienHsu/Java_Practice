# Reorder List

## You must do this in-place without altering the nodes' values.

```
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
```

## 錯誤點
- 要讓head後面切掉接續null
```java
mid.next = null;
```
- 這裡的概念是讓secondHead連到 cur.next，之後cur連上secondHead之後，secondHead直接變成下一個secondHead(存tmp內)，cur從下下個開始，因為要繼續接secondHead
``` java
while(secondHead != null) {
    ListNode tmp = secondHead.next;
    secondHead.next = cur.next;
    cur.next = secondHead;
    secondHead = tmp; ///
    cur = cur.next.next;////
```


- "Do it in place" means do not make a new LinkedList
- Problem is that we couldn't change the direction of right pointer.

## Solution

### We need to separate it to the sub-problems

- Find the Middle Node in LinkedList by using "FAST / SLOW Pointers"
- Return ListNode
```
1 -> 2 -> 3 -> 4 -> 5 -> null
     *f
*s

1 -> 2 -> 3 -> 4 -> 5 -> null
      ------->*f
---->*s

1 -> 2 -> 3 -> 4 -> 5 -> null
                ------->*f
     ---->*s

3 is the middle point 
Set 4 for the newHead of Second List

```

```java
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
```

- Reverse the second half linkedlist
(Still need to figure the methods)
```java
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
```

- Merge Two List with Left first
- Used the Dummy Node 
```java
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
```

- put all Methods togeter to solve the problems
```java
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

```