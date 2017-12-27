# Two Sum IV - Input is a BST

```
Example 1:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
Example 2:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False
```

# Solution - DFS
- Used a set to safe the number
- check if there is a target - root.val in the set (If no, add the current root into set)
- Then check the right and left root, directly return the boolean value.
```java
if(root == null) return false;
        if(set.contains(target - root.val)) return true;
        set.add(root.val);
        return helper(root.right, set, target) || helper(root.left, set, target);
```
