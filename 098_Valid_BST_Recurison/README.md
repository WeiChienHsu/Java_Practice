# Valid BST Recurison

- Used a helper Function
- Recurse Down: Pass Valid range down
```
helper(current_node, min, max)
```
- Return Up: Current subTree is valid or not
```
current Root, left && Right
```

```
               3 (3, min, max)
             /   \
(1, min, 3) 1     5  (5,3,max)
                /   \
    (4, 3, 5) 4      6  (6, 3, max)
```

```java
 private bollean isValidBST(TreeNode root, long max, long min) {
    if (root == null) {
        return true;
    }

    //Current Level : Check root.val

    if(root.val >= max || root.val <= min) {
        return false;
    }

    // Return Left and Right
    return isValidBST(root.left, root.val, min) && isValidBST(root.right, max, root.val); 
}
```

- Main function
```java
public boolean isValidBST(TreeNode root) {
    if(root = null) {
        return true;
    }
    return isValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE);
}
```

