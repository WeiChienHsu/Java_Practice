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
1   5

Find 9 and go through left subtree and Find 5
