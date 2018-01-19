# Merge K LinkedList

## Solution - Primitive Idea
- Heapify : O(k)
- Push / Poll single element : O(logK)
- Time Compliexity: O(K + ave(n) * KlogK)

- Used minHeap : store every Head in the Heap
- Set up a Dummy node and point to the peek
- prev pointer and cur pointer
- pop out the smallest and use d
```
D -> O
p    c

1. pre = dummy 
--------
2. cur = pop out point
3. pre.next = cur
4. pre = pre.next
--------
return dummy.next

```
- When you pop out the Point, add it's next point into Heap
```java
      for(ListNode n : lists) {
          if(n != null) {
              pq.add(n);
          }
      }
      
      ListNode dummy = new ListNode(0);
      ListNode prev = dummy;
      
      while(!pq.isEmpty()) {
          ListNode cur = pq.poll();
          if(cur.next != null) {
              pq.add(cur.next);
          }
          prev.next = cur;
          prev = prev.next;
      }
      return dummy.next;
  }
}
```
## Merge Sort
- Time: O(ave(n) * KlogK)
- Space: O(1)
- Recurisive Stack: O(logK)
```
list1   list2   list3  list4  list5
              /\
  list1 list2 list3     list4  list5            
        /       \              / \
  list1 list2   list3      list4  list5
    /   \         \ 
  list1    list2  list3
```
- mergeSort
```java
 private ListNode mergeSort(ListNode head1, ListNode head2) {
      if(head1 == null || head2 == null) {
          return head1 == null ? head2 : head1;
      }
      if(head1.val <= head2.val) {
          head1.next = mergeSort(head1.next, head2);
          return head1;
      } else {
          head2.next = mergeSort(head1, head2.next);
          return head2;
      }
  }
```
- Split
```java
  private ListNode split(ListNode[] lists, int start, int end) {
      if(start == end) return lists[start];
      int mid = start + (end - start) / 2;
      ListNode head1 = split(lists, start, mid);
      ListNode head2 = split(lists, mid + 1, end);
      return mergeSort(head1, head2);
  }
```