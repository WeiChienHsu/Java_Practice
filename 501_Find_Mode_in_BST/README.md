#  Find Mode in Binary Search Tree

## Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

```
Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
For example:
Given BST [1,null,2,2],
   1
    \
     2
    /
   2
return [2].
```

## Solution
- Give a map to record the freq  -> Map<Number, ferq> 
- Give a variable max to record the max ammount of modes
```java
        Map<Integer, Integer> map;
        int max = 0;
```

- Recursive Inorder funtion for traversal
* Go left and record the max -> map.getOrDefault(node.val,0) + 1
* Then Go right - left to record the max
```java
 public void inorder(TreeNode root) {
        if(root == null) return;
        if(root.left != null) inorder(root.left);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        max = Math.max(max, map.get(root.val));
        if(root.right != null) inorder(root.right);
    }
```

- Give a list to get all value from Map equal to the max -> map.get(key) == max
- Return a array record from a list
```java
 for(int key : map.keySet()){
            if(map.get(key) == max) list.add(key);
        }
        
        int[] res = new int[list.size()];
        
        for(int i = 0; i < res.length ; i++) {
            res[i] = list.get(i);
        }
```