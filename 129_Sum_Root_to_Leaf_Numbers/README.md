# Sum Root to Leaf Numbers

Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

```
For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
```

## Solution - Recursion
- 目的要得到下層傳回來的 sum ， 下層的 sum 是將 上層的 curSum * 10 + node.val 
- 上往下 : 下一層 node ， 當前 sum
- 下往上有幾種情況:
- 1. 超出leaf -> root == null : 代表已到達底，直接回傳0 (下層不帶有值)
- (每次到了下層，先檢查是否為null，再更新curSum)
- 2. 到達leaf -> root.right null && root.left == null : 代表該層為leaf，回傳 curSum 
- 3. 中間點 -> root 為中間節點，搜集左右兩節點回傳的值，加總後回傳上層

```java
public int dfsHelper(TreeNode root, int curSum) {
    
    if(root == null) {
        return 0;
    } 
    
    curSum =  curSum * 10 + root.val;
    
    if(root.left == null && root.right == null) {
        return curSum;
    }
    
    int leftSum = dfsHelper(root.left, curSum);  
    int rightSum = dfsHelper(root.right, curSum);
    return leftSum + rightSum;
}

```