# Path Sum

## Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

```
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1

Return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.       
```
## Solution

- Check Left and then Check right
- Make sure if that's the leaf (root.right and root.left == null)
- send the value of "sum - root.val" to the next Level
- Used root == null return false to send function back to last level

```java
    public boolean hasPathSum(TreeNode root, int sum) {
        
        if(root == null) return false;
    
        if(root.left == null && root.right == null && sum - root.val == 0) return true;
    
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
```
