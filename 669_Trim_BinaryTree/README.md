# Trim Binary Tree

## Solution - DFS
給一個限制大小 L 和 R，把給定的 Tree 中， 大於R 和 小於 L 的點去除， Return 一個新的BinarySearchTree

1. 判斷 Root ，如果 > R，代表他與其右邊的SubTree都要被拋棄，要把他的 Left SubTree 丟入 Recursive Function 找可用的Node
2. 判斷 Root ，如果 < L，代表他與其左邊的SubTree都要被拋棄，要把他的 Right SubtTree 丟入 Recursive Funciton 找可用的Node
3. 如果 Root 在範圍內， root.left -> 將 Left SubTree 丟入 Recursive Function 
4. 如果 Root 在範圍內， root.right -> 將 Right SubTree 丟入 Recursive Function
5. Recursive Function 的 Base case 是當 root == null 的時候， Return null 
6. Return Type 是 TreeNode
7. O(N)時間複雜度

```java
class Solution {
  public TreeNode trimBST(TreeNode root, int L, int R) {
      if(root == null) return null;
      if(root.val > R) return trimBST(root.left, L, R);
      if(root.val < L) return trimBST(root.right, L, R);
      
      root.left = trimBST(root.left, L, R);
      root.right = trimBST(root.right, L, R);
      
      return root;
  }
}
```