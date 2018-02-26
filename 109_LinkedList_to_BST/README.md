## Convert LinkedList to BST

- 1. Keep finding the Mid
- 2. Give a Head and a Tail
- 3. new a TreeNdoe(mid.val)
- 4. Divide and Conquer -> root.left / root.right
- 5. root.left(HEAD = head, TAIL = slow)
- 6. root.right(HEAD = slow.next, TAIL = tail)
- 7. BASE CASE: if(head == tail) return null

```java

class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null){
            return null;
        }
        
        ListNode tail = null;
        return buildTree(head, tail);
    }
    
    private TreeNode buildTree(ListNode head, ListNode tail) {
        if(head == tail) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast != tail && fast.next != tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        
        TreeNode root = new TreeNode(slow.val);
        root.left = buildTree(head, slow);
        root.right = buildTree(slow.next, tail);
        return root;
        
    }
    
}
```