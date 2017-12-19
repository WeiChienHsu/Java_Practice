# Binary Tree Upside Down
## Given a binary tree with a specific structure - all right nodes are either: 1. leaf nodes with a left sibling or 2. empthy.  

```
     1                4
    /  \             / \
   2    3   ->      5   2
  / \                  / \
 4   5                3   1

 ```

## Solution

### 4 
* Get 2 out and find 5
* point to 5
* point to 2
* Get rid of the pointers from 2

```
  2
 /
4 - 5

```
### 2
* Get 1 out and find 3
* point to 3
* point to 1
* Get rid of the pointers from 1

```
    1
   /
  2 - 3
 /
4 - 5
```

## Recurision 
- 假設剩下最後一層要處理
- 透過Root找到Root.left(新Root)的 right
- Root.left的 left 連回 Root
- 捨棄 Root的 left & right
- 重複一樣的動作，返回newRoot

```java
public TreeNode upsideDownBinaryTree(TreeNode root) {
    if(root == null || root.left == null) {
        return root;
    }

    // Assume all lower levels are handle
    TreeNode newRoot = upsideDownBinaryTree(root.left); 

    // Handle current level

    root.left.left = root.right;
    root.left.right = root;
    root.legt = null;
    root.right = null;

    return newRoot;
}
```
