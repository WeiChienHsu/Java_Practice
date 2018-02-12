# Minimum Depth of Binary Tree
###Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

## Soution - BFS (Level Traversal)
- 層級遍歷：進入下一層時，檢查現在的TreeNode是否兩個子數都為null，
- 如果兩子數都為null，直接回傳現在層級數
- 如果不為空，繼續遍歷下去。
- 利用一個Queue，依照層級加入Node，每一層開始前先用size()紀錄該層要走得點。

```java
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
        int curLevel = 1;
        queue.offerLast(root);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size ; i++) {
                TreeNode cur = queue.pollFirst();
                
                if(cur.left == null && cur.right == null) return curLevel;
                
                if(cur.left != null) {
                    queue.offerLast(cur.left);
                }
                
                if(cur.right != null) {
                    queue.offerLast(cur.right);
                } 
            }
            curLevel++;
        }
        
        return curLevel;
    }
}
```