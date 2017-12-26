# Max Depth in Tree

## Given a binary tree, find its maximum depth.

```
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
```

## Solution
- Find if there is a null in the next level.
- Return 0 if the root == null
- When recursively enter nexr level, the result plus one.
```java
return 1 + Math.max(maxDepth(root.left), maxDepth(root.right)
```