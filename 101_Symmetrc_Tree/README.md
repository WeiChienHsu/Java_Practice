# Symmtric

## Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

```
For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3
```

## Solution
- Follow what you've seen here
* root.left == root.right
* root.right.val == root.left.val
* root.right.left == root.left.right (val as well)
* root.left.left == root.right.right (val as well)

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return helper(root.left,root.right);
    }
    
    public boolean helper(TreeNode left, TreeNode right) {
        if(left == null || right == null) {
            if(left == right){
                return true;
            } else {
                return false;
            }
        }
            
        if(right.val != left.val){
            return false;
        } else{
            return helper(left.right, right.left) && helper(left.left, right.right);
        }
    }
}

```