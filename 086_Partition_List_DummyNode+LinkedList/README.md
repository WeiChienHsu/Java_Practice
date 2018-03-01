# 86 Partition List

### Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

- You should preserve the original relative order of the nodes in each of the two partitions.

```
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
```

## Solution

### First Thought

- Add a Small-List
- Add a Large-List
- Merge them together

#### Tool addtional Storage Space

### Better Thought In-place (Change pointers inside LinkedList with Dummy Node)

```
smallDummy.next = head (1->2->3)
-> smallDummy >>> 0 -> 1 -> 2 -> 3

smallDummy = smallDummy.next
-> smallDummy >>> 1 -> 2 -> 3

.next will return all value on the "next" pointer
```

### How to Run
- Make Three pointer (include the origin Head to show where are we now)

- Dummy Node should point to the Head with null(0) value; 

```java
    ListNode smallDummy = new ListNode(0);
    ListNode largeDummy = new ListNode(0);
        
    ListNode largeCur = largeDummy;
    ListNode smallCur = smallDummy;
```

- Meet the First Head.val:
* If < x : That is the head of smallCur, we should let smallCur point to it and move the samllCur to that number:

```java
if(head.val < x) {
    smallCur.next = head;
    smallCur = head;
```

* If >= x : That is the head of largeCur,
we should let largeCur point to it and move the largeCur to that number:

```java
largeCur.next = head;
    largeCur = head;
```
* After comparision, move the head to the next number
```java
head = head.next;
```

* Point the smallCur -> to largeDummy.next
* Make the largeCurr point to null (Last number avoid to repaeat)

```java
        smallCur.next = largeDummy.next;
        largeCur.next = null;
```

* Return Value next to first smallDummy value(0)
```java
return smallDummy.next; 
```


## Solution: BEST
```
1 -> 4 -> 3 -> 2 -> 5 ->2

small 0
large 0

dummySmall = 0
dummyLarge = 0

0 -> 1 -> 2 -> 2

0 -> 4 -> 3 -> 5

small.next = dummyLarge.next
large.next = null;

return dummySmall.next
```