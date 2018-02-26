# Reverse LinkedList

1 -> 2 -> 3 -> null
 null <- 1 <- 2 <- 3
 
```
1 -> 2 -> 3 -> null

give a pre = null

null  1 -> 2 -> 3 -> null
 p    h

give a ListNode next = head.next


null  1 -> 2 -> 3 -> null
 p    h    n
 
Keep reversing the Node untill head == null
 next = head.next
 h -> p
 p = h
 h = next;

 null <- 1 <- 2 <- 3  null
                   p    h
                        n

return pre
 ```

