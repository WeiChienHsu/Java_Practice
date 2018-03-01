# Intersection Two LinkedList
- Have INtersection
```
1 -> 2 -> 3 -> 3 -> 3 -> N

5 -> 3 -> 3 -> 3 -> N

a 1 -> 2 -> 3 -> 3 -> 3 -> 5 -> 3
b 5 -> 3 -> 3 -> 3 -> 1 -> 2 -> 3

Meet at 3!!!!
```
- No Intersection
```
1 -> 2 -> 3 -> 4 -> 5 -> N

5 -> 3 -> 3' -> 3'' -> N

a 1 -> 2 -> 3 -> 4 -> 5 -> 5 -> 3 -> 3' -> 3'' -> N
b 5 -> 3 -> 3' -> 3'' -> 1 -> 2 -> 3 -> 4 -> 5 -> N

Meet at Null!!!
```

We can use two iterations to do that. In the first iteration, we will reset the pointer of one linkedlist to the head of another linkedlist after it reaches the tail node. In the second iteration, we will move two pointers until they points to the same node. Our operations in first iteration will help us counteract the difference. So if two linkedlist intersects, the meeting point in second iteration must be the intersection point. If the two linked lists have no intersection at all, then the meeting pointer in second iteration must be the tail node of both lists, which is null.

```java
class Solution {
  public ListNode partition(ListNode head, int x) {
      ListNode small = new ListNode(0); // Less than x
      ListNode large = new ListNode(0); // More and Equal to x
      ListNode dummy1 = small; 
      ListNode dummy2 = large;
      
      while(head != null) {
          if(head.val < x) {
              small.next = head;
              small = small.next;
          } else {
              large.next = head;
              large = large.next;
          }
          head = head.next;
      }
      
      small.next = dummy2.next;
      large.next = null;
      
      
      return dummy1.next;
  }
}
```
