# Rotate List

Given a linked list, rotate the list to the right by k places, where k is non-negative.

```
Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL
```

## Solution

1. 每一次都在重複操作，找到 LinkedList 的倒數第二個點，將最後一個點存在 temp node 裡面，倒數第二個點指向Null，並且把 temp node 指向 head，然後 return temp node
2. 因為重複到原始長度的次數後，每一次rotation操作都沒有意義，當今天input非常大的時候，我們可以將k = k % length 來簡化操作。
3. 重複 k 次，將新的head return 得解。

```java
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return head;
        if(head.next == null) return head;
        
        // Situtaion that k is too long (ex. 200000)
        // We need to avoid the repeated rotation
        int length = 0;
		ListNode node = head;
		while(node != null){
			node = node.next;
			length++;
		}
		k = k % length;

        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        while(k > 0) {
            dummy.next = rotate(dummy.next);
            k--;
        }
        return dummy.next;
    }
    
    public static ListNode rotate(ListNode head) {
        ListNode cur = head;
        // Find the Last - 1 one to conect with the null
        while(cur.next.next != null) {
            cur = cur.next;
        }
        ListNode last = cur.next;
        cur.next = null;
        last.next = head;
        
        // Return the new head Node replaced by the Last node
        return last;
    }
}
```