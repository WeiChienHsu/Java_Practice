# Valid BST Recurison
- Give a reasonable Range!!!!!
- Different from the Iteration methods we compare each Node with the previous one.
- Recurse Down: Pass Valid range down


```
helper(current_node, min, max)
```
- Return Up: Current subTree is valid or not
```
current Root, left && Right
```

```
               3 (3, min, max)
             /   \
(1, min, 3) 1     5  (5,3,max)
                /   \
    (4, 3, 5) 4      6  (6, 3, max)
```

```java
 private bollean isValidBST(TreeNode root, long max, long min) {
    if (root == null) {
        return true;
    }

    //Current Level : Check root.val

    if(root.val >= max || root.val <= min) {
        return false;
    }

    // Return Left and Right
    return isValidBST(root.left, root.val, min) && isValidBST(root.right, max, root.val); 
}
```

- Main function
```java
public boolean isValidBST(TreeNode root) {
    if(root = null) {
        return true;
    }
    return isValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE);
}
```

## 分制法
```java
class ResultType{
    boolean isBalanced;
    int maxValue; //right
    int minValue; //left
    public ResultType(boolean isBalanced, int maxValue, int minValue) {
        this.isBalanced = isBalanced;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }
}
```
- 左邊回傳最小值 / 是否balance
- 右邊回傳最大值 / 是否balance
- 左右其中一個非平衡 -> false, 0, 0
- 左邊回傳的最大值 >= root.val -> false
- 右邊回傳的最小值 <= root.val -> false
- 其他狀況要回傳(true, Math.max(root.val, right.maxValue), Math.min(root.val, left.minValue))

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        ResultType rt = helper(root);
        return rt.isBalanced;
    }
    
    private ResultType helper(TreeNode root) {
        if(root == null) {
            return  new ResultType(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        if(!left.isBalanced || ! right.isBalanced) {
            return new ResultType(false, 0, 0);
        }
        
        if((root.left != null && left.maxValue >= root.val) ||
          (root.right != null && right.minValue <= root.val)){
            return new ResultType(false,0, 0);
        }
        
        return new ResultType(true, Math.max(root.val, right.maxValue), Math.min(root.val, left.minValue));
    }
    
    class ResultType{
        boolean isBalanced;
        int maxValue;
        int minValue;
        public ResultType(boolean ibt, int max, int min) {
            this.isBalanced = ibt;
            this.maxValue = max;
            this.minValue = min;
        }
    }
```