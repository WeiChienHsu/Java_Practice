# Pairs the LinkedList

- Pre / Cur / Then Pointers
- Dummy node is super!!
- Draw your target LinkList picture and make it happens step by step

```
D ->  1  ->  2   ->   3  ->   4   ->  N
p     c      t

D  ->  2  ->  1  ->  3 ....

Make  D -> 2  / 1 -> 3 / 2 -> 1 / p to 3

p.next = c.next;

D -> 2 -> 3 ...
p    t

1 -> 2 -> 3 ..
c    t

c.next = t.next

1  ->  3 ...
c

t.next = c

D -> 2 -> 1 -> 3 ...
p    t    c


p = c

D -> 2 -> 1 -> 3 ...
     t    c    p  ...
```

 ```java
class Solution {
  public ListNode swapPairs(ListNode head) {
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode pre = dummy;
      
      while(pre != null && pre.next != null && pre.next.next != null) {
          ListNode cur = pre.next;
          ListNode then = pre.next.next;
          
          pre.next = then;
          cur.next = then.next;
          then.next = cur;
          pre = cur;
      }
      
      return dummy.next;
  }
}

 ```