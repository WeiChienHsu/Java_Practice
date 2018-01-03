# Binary Tree Max Path Sum - Recursive
## Given a binary tree, find the maximum path sum.

```
For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
```

- Record the largest value by a global variable : left + right + root.val
- Keep tracking to the end : root == null return 0
- Compare to the left and right, choosing a larger number to send back last level:  return Math.max(left, right) + root.val;

```

        5
      /   \
     4     7
    / \   / \
   11  2 2   3

left = Math.max(0, maxPathDown(root.left));  5

        5
      /   \
    *4     7
    / \   / \
   11  2 2   3


left = Math.max(0, maxPathDown(root.left));  4

        5
      /   \
     4     7
    / \   / \
  *11  2 2   3

left = Math.max(0, maxPathDown(root.left));  11

        5
      /   \
     4     7
    / \   / \
   11  2 2   3
   root == null -> return 0

right = Math.max(0, maxPathDown(root.left));  11

        5
      /   \
     4     7
    / \   / \
   11  2 2   3
   root == null -> return 0

        maxValue = Math.max(maxValue, left + right + root.val); --> 11
        return Math.max(left, right) + root.val; --> 11

right = Math.max(0, maxPathDown(root.right));  4

        5
      /   \
     4     7
    / \   / \
   11  *2 2   3

        maxValue = Math.max(maxValue, left + right + root.val); --> 2
        return Math.max(left, right) + root.val; --> 2

->> Send back the larget number from 4 sub tress to 5

```
