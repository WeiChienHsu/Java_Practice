# Palindrome LinkedList
- Don't use additional Space with O(n) time compliexity
- 1. Find the middle node by using fast and slow pointer
- 2. Reverse the middle point (slow pointer)
- 3. Reassign the HEAD to the fast pointer
- 4. Compare slow and first

- 重頭到尾都沒有 new 一個新的 LinkedList Object
- 不段變化 fast and slow 的 reference

```
1 -> 2 -> 2 -> 1 -> null
f
s

1 -> 2 -> 2 -> 1 -> null
          f
     s

1 -> 2 -> 2 -> 1 -> null
                      f
          s

Reverse Slow = reverse(slow)

null <- 2 <- 1 
             s

Reassign Fast = Head

1 -> 2 -> 2 -> 1 -> null
f

Compare slow and first step by step

1 -> 2 -> 2 -> 1 -> null
f

1 -> 2 -> null
```     

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        
        // Find Middle Node
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse Slow Pointer
        slow = reverse(slow);
        fast = head;
            
        // Reassign head to first and Compare with slow
        while(slow != null) {
            if(slow.val != fast.val) {
                return false;
            }
            
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    
    }
    private ListNode reverse(ListNode cur) {
        ListNode pre = null;
        while(cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}
```