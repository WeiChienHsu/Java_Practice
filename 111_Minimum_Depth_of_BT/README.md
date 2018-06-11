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

## Solution - DFS
- 處理左子樹
- 處理右子樹
- Base Case: 當 root == null return 0
- 回傳值： 當 right == 0 或 left == 0 代表一邊的樹沒有child，回傳當前Node下面的 left + right + 1，如果左右都有子樹，回傳 left 和 right 小的那個。

```java
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (right == 0 || left == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }
}
```