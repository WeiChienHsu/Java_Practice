## LinkedList Circle find Node



- Assume the start of cycle is dx away from head of list and the cycle has length y. They meet inside the cycle with dy offset from the start of the cycle.

```
distance walked by slow= (dx+my+dy)
distance walked by fast= (dx+ny+dy)
```

-  since they meet:
```
2*(dx+my+dy)=(dx+ny+dy)
```

- in the end we have:
```
dx+dy=(n-2*m)y
```

- Usually, you can set n=1 and m=0, you have:
```
dx+dy=y
```

- if dx>y, dy<0 simply means they meet inside the cycle but dy steps before the start of the cycle.

- now, you know

```
dx=y-dy
```

- it means, if one start from the head , one start from where they meet before, they will meet again at exactly the start of the cycle. (Special case: dx=0, so one should check if they already meet )

```
                 <- <- <- <- 
                 |         |
  O -> O -> O -> O -> O -> O 
  s                           
  f              
  st 


                 <- <- <- <- 
                 |         |
  O -> O -> O -> O -> O -> O 
       s                           
            f              
  st 

                 <- <- <- <- 
                 |         |
  O -> O -> O -> O -> O -> O 
            s                           
                      f              
  st 


                 <- <- <- <- 
                 |         |
  O -> O -> O -> O -> O -> O 
                *s                           
                *f              
  st 


                <- <- <- <- 
                 |         |
  O -> O -> O -> O -> O -> O 
                     *s                           
                *f              
       st 

                <- <- <- <- 
                 |         |
  O -> O -> O -> O -> O -> O 
                           *s                           
                *f              
           st 

                <- <- <- <- 
                 |         |
  O -> O -> O -> O -> O -> O 
                *s                           
                *f              
                st 

```




```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        ListNode start = head;
        
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                // when there is a circle
                while(slow != start) {
                    slow = slow.next;
                    start = start.next;
                }
                return start;
            }  
        }
        // There is no circle
        return null;
    }
}
```