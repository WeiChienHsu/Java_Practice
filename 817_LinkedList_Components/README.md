# LinkedList Components

We are given head, the head node of a linked list containing unique integer values.

We are also given the list G, a subset of the values in the linked list.

Return the number of connected components in G, where two values are connected if they appear consecutively in the linked list.

```
Example 1:

Input: 
head: 0->1->2->3
G = [0, 1, 3]
Output: 2
Explanation: 
0 and 1 are connected, so [0, 1] and [3] are the two connected components.
Example 2:

Input: 
head: 0->1->2->3->4
G = [0, 3, 1, 4]
Output: 2
Explanation: 
0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.
```

## Solution

將 int[] G 放入 set ， 檢查是否存在。

如果 node 存在著目標，就繼續向前進，檢查下個點是否存在。

如果下個也存在，繼續往前走到下個不是目標的，如果一開始不存在，直接走到下個存在點。

```java
class Solution {
    public int numComponents(ListNode head, int[] G) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < G.length; i++) {
            set.add(G[i]);
        }
        
        ListNode cur = head;
        int count = 0;
        while(cur != null) {
            if(set.contains(cur.val)) {
                count++;
                while(cur.next != null && set.contains(cur.next.val)) {
                    cur = cur.next;
                }   
            }
            cur = cur.next;     
        }
        return count;
    }
}
```