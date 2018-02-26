# Reverse Linked List II

- Reverse a linked list from position m to n. Do it in-place and in one-pass.

```
For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.
```

- 1. Dummy(0).next point to head
- 2. Give a ListNode pre refer to duummy
- 3. Move pre front m-1 step
- 4. Give a start and then behind
```
0  ->   1   ->   2   ->   3  ->  4  ->   5  ->   N
d      pre     start     then

```
- How to Reverse:
```
0  ->  1   ->  3    ->    2    ->    4   ->   5  ->   N   
                        _______________
            _________________        
      ___________

start.next = then.next   2 -> 4
then.next = pre.next  3 -> 2
pre.next = then   1 -> 3
then = start.next


0  ->  1   ->  3    ->    2    ->    4   ->   5  ->   N   
      pre               start       then              
                                 _______________
                        _________________        
                ___________

0   ->  1    ->   4   ->   3   ->   2  ->  5   ->  N

start.next  = then.next  2 -> 5
then.next = pre.next  4 -> 3
pre.next = then 1 -> 4
then = start.next
```
- 5. Return dummy.next

```java
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null){
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        
        for(int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }
        
        ListNode start = pre.next;
        ListNode then = start.next;
        
        for(int j = 0; j < n - m; j ++){
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }
        
        return dummy.next;
     }
}

```