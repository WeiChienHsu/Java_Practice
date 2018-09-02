# Binary Tree Vertical Order Traversal
```
Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7 

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]

```

## Solution
Used two queues to record both TreeNode and Value of Col, since we need to deal with each node by a level teaversal.
1. Store root node and 0 as col.
2. Record the min and max value of cols.
3. Used a Map to record the specific col pairs with the nodes
4. Level Traversal the whole tree from left to the right. (Left child access col - 1, and Right child access col + 1)
5. Used the min and max to loop through our map.


```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        /* Stored the current TreeNode */
        Deque<TreeNode> queue = new ArrayDeque<>();
        /* Stored the current col */
        Deque<Integer> cols = new ArrayDeque<>();
        /* Key - Col, Value - Value of the TreeNode */
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        /* Boundry of columns */
        int min = 0;
        int max = 0;
        
        if(root == null) return results;
        
        queue.offerLast(root);
        cols.offerLast(0);
        
        while(!queue.isEmpty()) {
            TreeNode current = queue.pollFirst();
            int col = cols.pollFirst();
            
            if(!map.containsKey(col)) {
                map.put(col, new ArrayList<>());
            }
            
            map.get(col).add(current.val);
            
            /* Left child access col - 1 */
            if(current.left != null) {
                queue.offerLast(current.left);
                cols.offerLast(col - 1);
                min = Math.min(min, col - 1);
            }
            
            /* Right child access col + 1 */
            if(current.right != null) {
                queue.offerLast(current.right);
                cols.offerLast(col + 1);
                max = Math.max(max, col + 1);                
            }
        }
        
        for(int i = min; i <= max; i++) {
            results.add(map.get(i));
        }
        
        return results;
    }
}
```