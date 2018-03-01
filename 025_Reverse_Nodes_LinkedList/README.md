# Reverse Node

## Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.


```
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
```

### How to Reverse LinkedList
```
null   1  ->  2  ->  3  ->  4
  p   cur
      tmp

while cur != null {
tmp = cur.next;
cur.next = pre;
pre = cur;
cur = tmp
} 

null <-  1   2  ->  3  ->  4
         p  cur
            tmp
```

### Logic of subList
```

D -> O -> O | O -> O

1. Reverse

D -> O <- O | O -> O

2. Save tail point to the second head

D -> O <- O | O -> 0
     |        |
      --------

3. pointer to the preHead change to the newHead

D  0 <- 0 | 0  -> 0 | 
|__|____|   |
   |________|

```

- Separate each K number : we need two pointers
* Head: Track the number next we need to connect wiht
* Tail: Deal with the reverse
```java
    ListNode pre = dummy;
    
    ListNode tail = preHead.next;
    ListNode cur = tail.next;
```
- Separate each K counts
```java
while (head != null) {
    count--; // Move untial count = 0
    // Reverse every K steps
    if(count == 0) {
        pre = reverse(pre, head.next);
        head = pre.next;
        count = k;
    } else {
        head = head.next;
    }
```
- Used Dummy node to Unify the first subproblem with all others, and in the end, return the dummy.next
```java
ListNode dummy = new ListNode(0);
        dummy.next = head;
        // pre.next points to head of sublist to be reversed
        ListNode pre = dummy;
        int count = k;

return dummy.next;
```

- Reverse the sub-problems (Need to add a tail to save the next head)
```java
    private ListNode reverse(ListNode preHead, ListNode nextHead) {
        ListNode tail = preHead.next;
        ListNode cur = tail.next;
        while (cur != nextHead) {
            ListNode tmp = cur.next;
            cur.next = preHead.next;
            preHead.next = cur;
            cur = tmp;
        }
        tail.next = nextHead;
        return tail;
    }
```

## Other Solution

- Dummy node 指向 Head
- ListNode pre = dummy
- 需要一個 reverseNextKNodes(pre, k)
```
pre -> n1 -> n2 ... -> nk -> nk+1
pre -> nk -> nk-1 ..-> n1 -> nk+1
```

- 判斷是否有足夠的K ， 如果沒有 return null

## reverseNextKNodes(pre, k)
- Input : pre  (pre -> n1 -> n2 -> .. nk -> nk+1)
- 要得到: pre -> nk -> ..-> n2 -> n1 -> nk+1
- Return: nk+1

- 第一步: ListNode n1 = head.next;
- 第二步: ListNode cur = head;
- 第三部: cur 要走到 nk 的地方
- 第四部: 用 for loop 讓 cur 走到 nk，順便判斷是否足夠n長度，不夠的話return null (不做操作)
```java
for(int i = 0; i < k; i++) {
    cur = cur.next;
    if(cur == null) {
        return null;
    }
}
```
- 第五部: ListNode nk = cur
- 第六部: ListNode nkPlus = cur.next (要被回傳的值)
- 第七部: Reverse: pre -> Nk -> n1

```java
ListNode pre = head;
ListNode cur = haed.next;
while(cur != nkPlusOne){
    ListNode temp = cur.next;
    cur.next = pre;
    pre = cur;
    cur = temp
}
```

- 第七部: pre -> nk / n1 -> nk+1 / return n1
```java
head.next = nK;
n1.next = nKplusOne
return n1
```