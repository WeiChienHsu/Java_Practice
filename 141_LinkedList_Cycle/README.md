# LinkedList Cycle

## Given a linked list, determine if it has a cycle in it.

## Solution
```
Let us take 2 pointers namely slow Pointer and fast Pointer to traverse a Singly Linked List at different speeds. A slow Pointer moves one step forward while fast Pointer moves 2 steps forward
```

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode fast = head;
        ListNode slow = head;
        
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            
            if(slow == fast) return true;
        }
        
        return false;
    }
}
```