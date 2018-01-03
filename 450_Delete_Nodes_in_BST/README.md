# Remove Nodes in BST

## Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

### Basically, the deletion can be divided into two stages:

- Search for a node to remove. If the node is found, delete the node.

```
root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
```

## Solution:
- Case 1 : there are two children in the key node
- Case 2 : there is one or no chuld in the key node
* No child: Directly remove
* One Child: Previous one Point to the child

- For the first situation, we need to continue searching node with "largest value in left substree", and then it'll become the case 2 for only one or no child.
- Since the largest in right and the smallest in left will be the closest one with the key node

```
Key = 9

      12
     / 
    9   
   / \   
  2    10  
 / \
1  *5

Find 9 and go through left subtree and Find 5
->> Change value 9 to 5

      12
     / 
   *5   
   / \   
  2    10  
 / \
1  *5

--> Remove 5 in the leaf
      12
     / 
   *5   
   / \   
  2    10  
 / 
1 
```

## Used dummy node to deal with root
```java
    TreeNode dummy = new TreeNode(Integer.MIN_VALUE);
    dummy.left = root;
    TreeNode prev = dummy;
    TreeNode cur = root;
```
## Find the Node with key
- key is smaller, go left
- key is larger, go right
```java
        while(cur != null && cur.val != key) {
            prev = cur;
            if(cur.val < key) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
```

## If no found, return the whole tree
```java
if(cur == null) return dummy.left;
```

## Found the key, give a pointer "target" to it
```java
TreeNode target = cur;
```

## Two Children
- if left and right not null, keeping going left, until left is null (but right not null), go right!
- Change the target value to that key value
```java
if(cur.left != null && cur.right != null) {
    prev = cur;
    cur = cur.left;
    while(cur.right != null) {
        prev = cur;
        cur = cur.right;
    }
    // Change value  
    target.val = cur.val;
}
```

## One or No child
- Now the node only have one child or no child
- For no child (cur.left == null):
* if node on the pre left, give the cur right to it
* if node on the pre right, gitve the cur right to it
- For onw child on the left side:
* if node on the pre left, give the cur left to its left side
* if node on the pre right, gitve the cur left to its left side

```java
        if(cur.left == null) {
            if(prev.left == cur) {
                prev.left = cur.right;
            } else {
                prev.right = cur.right;
            }
        } else {
            if(prev.left == cur) {
                prev.left = cur.left;
            } else {
                prev.right = cur.left;
            }
```
- Return the dummy left (whole tree
```java
return dummy.left;
```