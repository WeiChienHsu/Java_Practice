# Merge Two Sorted LinkedList -> Like merge Sort


## Iteration - With dummy node
- new A dummy Node
- Give a pointer Pre to it
- Two Pointer on l1 and l2 (Dont need to new, just use the originl one)
- If node val is greater than one another, add it next to pre, and move itself to the next.
- After each time we moved l1 or l2, pre need to move next!
- Check the situation if l1 and l2 have different length
- Connect the rest of the l1 or l2 (if needed)


```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null){
            return null;
        }
        
        if(l1 == null){
            return l2;
        }
        
        if(l2 == null){
            return l1;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        
        while(l1 != null && l2 != null) {
            if(l1.val > l2.val) {
                pre.next = l2;
                l2 = l2.next;
            } else {
                pre.next = l1;
                l1 = l1.next;
            }
            pre = pre.next;
        }
        
        if(l1 == null) {
            pre.next = l2;
        }
        
        if(l2 == null){
            pre.next = l1;
        }
        
        return dummy.next;

    }
}
```