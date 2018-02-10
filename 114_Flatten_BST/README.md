# Flatten BST

## For example,
```
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
```

## Solution
- Left and Right 兩邊分別完成一棵樹
- 便利得到leftLast和rightLast
- leftLast.right = root.right
- root.right = root.left
- root.left = null

```java
class Solution {
  public void flatten(TreeNode root) {
      helper(root);
  }
  
  private TreeNode helper(TreeNode root) {
      if(root == null) {
          return null;
      }
      
      TreeNode leftLast = helper(root.left);
      TreeNode rightLast = helper(root.right);
      
      if(leftLast != null) {
          leftLast.right = root.right;
          root.right = root.left;
          root.left = null;
      }
      
      if(rightLast != null) {
          return rightLast;
      }
      
      if(leftLast != null) {
          return leftLast;
      }
      
      return root;
  }

  }
```