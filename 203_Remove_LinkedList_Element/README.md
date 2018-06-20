# Remove LinkedList Element

Remove all elements from a linked list of integers that have value val.
```
Example:

Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
```
## Solution

- 每次檢查完一次當前值後，current向前，到達null停止
- 當current.val == target的時候，prev.next = current.next，跳過當前的點
- 如果該值不為target，prev繼續向前


```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode current = head;
        ListNode prev = dummy;
        
        while(current != null) {
            if(current.val == val) {
                prev.next = current.next;
            } else {
                prev = prev.next;
            }
            current = current.next;
        }
        return dummy.next;
    }
}
```