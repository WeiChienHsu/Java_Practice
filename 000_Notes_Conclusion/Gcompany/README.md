# 686 Repeated String Match

The idea is to keep string builder and appending until the length A is greater or equal to B.

```java
 public int repeatedStringMatch(String A, String B) {

    int count = 0;
    StringBuilder sb = new StringBuilder();
    while (sb.length() < B.length()) {
        sb.append(A);
        count++;
    }
    if(sb.toString().contains(B)) return count;
    if(sb.append(A).toString().contains(B)) return ++count;
    return -1;
}
```

# 687

- 要讓左子樹與右子樹回傳最大值，且該值符合以下判斷：取決於子數與父樹數值是否相等？
- 紀錄當前最長的值 ： 左節點長 + 右節點長

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
  public int maxHeight = 0;
  public int longestUnivaluePath(TreeNode root) {
      if(root == null) return 0;
      getHeight(root, root.val);
      return maxHeight;
  }
  
  public int getHeight(TreeNode node, int val) {
      if(node == null) return 0;
      //  Get the Height from left and right
      int left = getHeight(node.left, node.val);
      int right = getHeight(node.right, node.val);
      // if node value == fater node value -> return Max(right or left) 
      // if node value != father node value -> return 0
      // Record the current Hieght(Since the left and right always equal to their father's value or they wont be greater than 0)
      maxHeight = Math.max(left + right, maxHeight);
      if(val == node.val) return Math.max(left, right) + 1;
      return 0;
  }
}
```