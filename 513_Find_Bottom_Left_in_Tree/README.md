# Find Bottom Left in Tree
```
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
```
## Solution 
- Tree Level Traversal Question 
- Used Queue to solve the problem
- Record the first Value in the each level (recording by a boolean isFirst) and add the number out of the for loop

```java
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        int[] res = new int[1];
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            boolean isFirst = true;
            for(int i = 0; i < size; i++) {
                TreeNode cur = queue.pollFirst();
                if(cur.left != null) queue.offerLast(cur.left);
                if(cur.right != null) queue.offerLast(cur.right);
                if(isFirst) {
                    res[0] = cur.val;
                    isFirst = false;
                }
            }
        }
        return res[0];
    }
}
```
