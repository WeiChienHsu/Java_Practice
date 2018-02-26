# Remove Duplicate in LinkedList

- We need two Pointers Pre and Cur
- Cur: keep treaking if there is a duplicate number(if yes, move to the next, if no, stop give job to the Pre Pointer)
- Pre: To check if Cur pointer has moved or not (if yes, point to the cur.next to skip all duplicate number, if no move ahead to next node)
- After Pre pointer move or connect, cur move to the next node to keeping doing it's job
- Dummy node is quite important!!!!

```

D  ->  1  ->  1   ->  1  ->  2  ->  3  ->  3  -> N
P      C

c.next.val = c.val
C = C.next;


D  ->  1  ->  1   ->  1  ->  2  ->  3  ->  3  -> N   
P                     C

p.next != C -> p.next = C.next
C = C.next

D  ->  2  ->  3   ->   3   ->  N
P      C

p.next = c.next
p = p.next
c = c.next

D  ->  2  ->  3   ->   3   ->  N
       P      C

c.val = c.next.val
c = c.next  


D  ->  2  ->  3   ->   3   ->  N
       P               C

```


```java
class Solution {
   
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        
        while(cur != null) {
            
            while(cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            
            if(pre.next != cur) {
                pre.next = cur.next;
            } else {
                pre = pre.next;
            }
            cur = cur.next;
            
        }
        return dummy.next;
    }
}
```