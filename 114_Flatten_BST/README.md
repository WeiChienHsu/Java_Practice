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

## Solution - No recursion
只要root.left != null，找到左邊subtree最右邊的Node，將這個點 -> root.right，然後將root.right連接到root.left

1. 檢查 root.left 是否存在
2. 用pre pointer指向root.left，並找到其子樹最右邊的點
3. 將該點指向root.right
4. 將root指向root.left

```java
class Solution {
    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while(cur != null) {
            if(cur.left != null) {
                TreeNode pre = cur.left;
                // Find the rightMost
                while(pre.right != null) {
                    pre = pre.right;
                }
                
                pre.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }
}
```