# Binary Tree Tilt

Given a binary tree, return the tilt of the whole tree.

The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.

The tilt of the whole tree is defined as the sum of all nodes' tilt.
```
Example:
Input: 
         1
       /   \
      2     3
Output: 1
Explanation: 
Tilt of node 2 : 0
Tilt of node 3 : 0
Tilt of node 1 : |2-3| = 1
Tilt of binary tree : 0 + 0 + 1 = 1
```

## Solution
```
        1
      /   \
    2       3
  /   \       \  
 5    3        6

 Node 5 = 0
 Node 3 = 0
 Node 2 = 5 - 3 = 2
 Node 3 = 0 - 6 = 6
 Node 1 = (5+2+3) - (3+6) = 1

 Sum = 2 + 6 + 1 = 9
 ```

sumOfSubtree : 紀錄該點以下的左右子樹與該點總和。
findTilt : 紀錄該點左子樹與右子樹的絕對值差，並且往左與右找下去。

```java
class Solution {
  // 紀錄該點左子樹與右子樹的絕對值差，並且往左與右找下去。
    public int findTilt(TreeNode root) {
        if(root == null) return 0;
        int curVal = Math.abs(sumOfSubtree(root.left) - sumOfSubtree(root.right));
        return curVal + findTilt(root.left) + findTilt(root.right);
    }

  // 紀錄該點以下的左右子樹與該點總和。
    public int sumOfSubtree(TreeNode root) {
        if(root == null) return 0;
        return root.val + sumOfSubtree(root.left) + sumOfSubtree(root.right);
    }
}
```