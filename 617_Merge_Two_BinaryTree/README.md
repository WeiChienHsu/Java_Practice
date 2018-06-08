# Merge Two Binary Tree

## Solution - Recursion(Inorder)

1. 每一輪都先檢查左右支點是否都為 NULL -> 不需處理，直接 Return NULL

2. 從兩個 Tree 當中取出該 Root 的 Value，並且附加在新的Node上
```java
int val = (root1 != null? root1.val : 0) + (roo2 != null? root2.val : 0);
TreeNode newNode = new TreeNode(val);
```

3. 並且 Inorder Treversal 這棵樹，放入左右之點前，都必須要檢查當前的root是否為null，因為無法call出null.right的數值
```java
newNode.left = helper(root1 != null? root1.left : null, root2 != null? root2.left : null)
```

4. 最終 Return 該次 Recursion 所產生的新 Node
```java
return newNode
```


