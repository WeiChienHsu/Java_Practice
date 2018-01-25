# Balanced Binary Tree
- Given a binary tree, determine if it is height-balanced.
```
For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
```

## Solution

- Checks whether the tree is balanced strictly 
- according to the definition of balanced binary tree: the difference between the heights of the two sub trees are not bigger than 1, 
- both the left sub tree and right sub tree are also balanced. With the helper function height(), we could easily write the code
```java
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        int left = height(root.left);
        int right = height(root.right);
```
```java
        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
```

- How to Count the Height?
- Used the max value from left and right and then plus one to get the highest height return back from next level
```java    
    private int height(TreeNode root) {
        if(root == null) return 0;
        int left = height(root.left);
        int right = height(root.right);
        
        return Math.max(left, right) + 1;
    }
```
