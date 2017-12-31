# Valid Binary Search Tree - Iteration


## Given a binary tree, determine if it is a valid binary search tree (BST).

- If we gave a in-order travesal and get an array, then we could check if this array is from the small to the large.

```
Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
```
## Iteration Solution
- Search all left nodes first and save into a stack until meet to the null then pop out the number from Stack to compare with previous node.

- Then, try to search the righ nodes, and agian, check if there is left nodes. If yes, search the left nodes to the end as usual. 

- Everytimes, meet null. Pull out the number in Stack.

```java

while(cur != null || !stack.isEmpty()) {
    
    // go Left for all nodes
    
    if(cur != null) {
        stack.offerLast(cur);
        cur = cur.left;
    } else { // When left side meet the end poll out the node to compare with last node and go right
        cur = stack.pollLast();
        if (pre != null && cur.val <= pre.val) return false;
        pre = cur;
        cur = cur.right;
    }
}

return true;
}

```