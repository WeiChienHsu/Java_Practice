# Count the complete tree node  
## Given a complete binary tree, count the number of nodes.

## Solution
- Directly count the height of tree and used (1<< height) to count the number of nodes.
```
    O
   / \
  O   O
 / \ / \
O  O O  O

h = 1 -> (1 << 1) = 2
h = 2 -> (1 << 2) = 4
0010 ->  0100

```
- There are two sitution in this complete tree

- Left is Full

```
    O
   / \
  O   O
 / \ / 
O  O O  
```
* Count the right
```java

```

- Left is not Full
```
    O
   / \
  O   O
 /   
O     
```

* Count the last level, then count the left

```java
    public int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }
```
```java
public int countNodes(TreeNode root) {
int h = height(root);
return h < 0 ? 0 :
    // check if the left subtree is full with left and right nodes
    height(root.right) == h-1 ? (1 << h) + countNodes(root.right) // root.right.legt have node
                                : (1 << h - 1) + countNodes(root.left);
```
