# Longest Univalue Path

- Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

- Note: The length of path between two nodes is represented by the number of edges between them.

```
Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output:

2
Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5
Output:

2
```

## Solution - DFS

- 要讓左子樹與右子樹回傳最大值，且該值符合以下判斷：取決於子數與父樹數值是否相等？
- 紀錄當前最長的值 ： 左節點長 + 右節點長

```
    3 *
   / \
  4   4
 / \   \ 
4  4    3
```
left = getHeight(3.left, 3);

```
    3 
   / \
  4*  4
 / \   \ 
4  4    3
```
left = getHeight(4.left, 4);
|
left = 1
rithg = 1
maxHeight = 2

```
    3 
   / \
  4   4
 / \   \ 
4*  4    3
```
left = 0
right = 0
maxHeight = 0
val == node.val -> return 1

- if node value == fater node -> return max(left,right) + 1
- if not return 0
- Record the Max value by comparing (left + right, currentHeight)
