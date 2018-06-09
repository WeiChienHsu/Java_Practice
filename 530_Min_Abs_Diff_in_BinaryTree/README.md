# Find Min Abs Diff in BinarySearch Tree

## Solution - inOrder Traversal

1. 使用inOrder Traversal，先到達最左下方的 Node，（此時無prev數值）將該值紀錄為 prev，並且回到上一層，用prev與current Node相減得到一差，比較該值與目前最小值的差別，並將此值紀錄為新的prev，進入右下方的Node與prev比較。
2. 每一層所使用的prev，都會保留在該次的recursive call裡面，因為我們使用 ArrayList 和 List 來去紀錄 prev 以及 minDiff。
3. 當遇到 null 的時候，直接 return 不處理


```java
  public static void helper(TreeNode root, int[] minDiff, List<TreeNode> prev) {
      if(root == null) return;
      // Traversal to the Left
      Solution.helper(root.left, minDiff, prev);
      if(prev.isEmpty()) {
          // Haven't add the previous node into array
          prev.add(root);
      } else {
          // Compare the current Node with previous Node
          minDiff[0] = Math.min(minDiff[0], Math.abs(root.val - prev.get(0).val));
          // Update the current Node for the current right child 
          prev.set(0, root); // Replace the existed Node
      }
      // Traversal to the Right
      Solution.helper(root.right, minDiff, prev);
  }
```