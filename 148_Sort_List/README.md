# Sort List

## Solution - Merge Sort

1. 先使用 fast, slow, prev pointer 來切分 LinkedList，並且divide & conquer 左邊的sublinkedlist和右邊的sublinkedlist。

```java
ListNode slow = head, fast = head, prev = null;

while(fast != null && fast.next != null) {
  prev = slow;
  slow = slow.next;
  fast = fast.next.next;
}
prev.next = null

ListNode left = doSort(head);
ListNode right = doSort(slow);

```
```
1 -> 2 -> 3 -> 4
s
f


1 -> 2 -> 3 -> 4
p    s
          f

1 -> 2 -> 3 -> 4 -> null
     p    s
                     f

1 -> 2 -> null
3 -> 4 -> null

```

2. 結束了分割後，將個切分開來的Node排序再一起，使用merge，邏輯上是先使用一個Dummy Node，聯繫上數值較小的那個Node，比較 ListNode l1 和 ListNode l2，如果 l1.val < l2.val，DummyNode.next = l1，l1 = l1.next，一直比較到一方為null，代表剩下的點都是大於dummy node後面的點。 將剩下的點補在Dummy Node這串後面，然後return dummy.next

```java
ListNode dummy = new ListNode(0);
ListNode current = dummy;

while(left != null && right != null) {
  if(left.val < right.val) {
    current.next = left;
    left = left.next;
  } else {
    current.next = right;
    right = right.next;
  }
  current = current.next;
  
}

while(left != null) {
  current.next = left;
  left = left.next;
}

while(right != null) {
  current.next = right;
  right = right.next;
}

return dummy.next;

```