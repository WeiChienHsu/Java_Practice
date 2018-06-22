# Split Linked List in Parts

Given a (singly) linked list with head node root, write a function to split the linked list into k consecutive linked list "parts".

The length of each part should be as equal as possible: no two parts should have a size differing by more than 1. This may lead to some parts being null.

The parts should be in order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal parts occurring later.

Return a List of ListNode's representing the linked list parts that are formed.

```
Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]

Example 1:
Input: 
root = [1, 2, 3], k = 5
Output: [[1],[2],[3],[],[]]
Explanation:
The input and each element of the output are ListNodes, not arrays.
For example, the input root has root.val = 1, root.next.val = 2, \root.next.next.val = 3, and root.next.next.next = null.
The first element output[0] has output[0].val = 1, output[0].next = null.
The last element output[4] is null, but it's string representation as a ListNode is [].
```
## Solution - LinkedList Traversal
題目給定 k 代表著我們需要將一串未知長度的Linked List分散到k組中，而每一組的長度差別不超過1。

由 number / k 可以得到每組最少有多少個，由 number % k 可以得到開始的前幾組需要多一個element。

計算 len
```java
int len = 0;
for (ListNode node = root; node != null; node = node.next)
    len++;
```

所以我們利用: n = len / k, hasOne = len % k 

加上兩層的for lopp，第一層處理每次加入array的操作，以及LinkedList的遍歷，所以要加入 cur != null

```java
ListNode cur = root, prev = null;

for(int i = 0; i < k && cur != null; i++, hasOne--) {
  // 加入 當前的 LinkedList head
  result[i] = cur;
  // 每 n 個數字填入一次，判斷是否是前 hasOne 個數字
  for(int j = 0; j < n + (hasOne > 0 ? 1 : 0); j++) {
      pre = cur;
      cur = cur.next;
  }
  prev.next = null;
}
```