# Sume of Left Tree

## Find the sum of all left leaves in a given binary tree.

```
Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
```

## Solution - recursion

- Check the root left and move to root.right to use a same method
```
    O
  /    \
 null  null 

    O
  /    \
 null   O    -> Need to check if there is left

     O
  /    \
 O    null   -> Doesn't matter, just check left


          0
        /   \
       0 
      / \ 
     0  null       -> Need to check left

          0
        /   \
       0 
      / \ 
    Null 0             -> Still need to check right


          0
        /   \
       0 
      / \ 
    Null  Null  -> Only in this situtaion we could make sure  O is left leave

 ```

```
    if root.left == null && root.right == null -> 0
    if root.left == null && root.right != null -> check right
    if root.left != null -> check left
    if root.left != null & root.left.left == null 
            1. root.left.right == null -> return root.left
            2. root.left.right != null -> check root.right
 ```

```java
class Solution {
public int sumOfLeftLeaves(TreeNode root) {
if(root == null) return 0;
int res = 0;
if(root.left != null) {
    if(root.left.left == null && root.left.right == null){
        res += root.left.val;  
    } else {
        res += sumOfLeftLeaves(root.left);
    }      
} 
res += sumOfLeftLeaves(root.right); 

    return res;
}
```