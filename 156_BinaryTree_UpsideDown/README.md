# Binary Tree Upside Down
## Given a binary tree with a specific structure - all right nodes are either: 1. leaf nodes with a left sibling or 2. empthy.  

## Reverse it into a new tree where the origin al right nodes becoming new left leaf nodes. Return the new root.

```
     1                4
    /  \             / \
   2    3   ->      5   2
  / \                  / \
 4   5                3   1

 ```
## Solution
- Start from leftmost leaf
- Store all nodes along th path in stack
- Find a loop for tree transformation
```
|  4  |
|  2  |
|__1__|


4 
* Get 2 out and find 5
* point to 5
* point to 2
* Get rid of the pointers from 2

  2
 /
4 - 5

2
* Get 1 out and find 3
* point to 3
* point to 1
* Get rid of the pointers from 1

    1
   /
  2 - 3
 /
4 - 5


```
- Time Coplexity : O(n)
- Space Complexity : O(n)

