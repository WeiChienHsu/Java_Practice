# Subtree of Another tree
Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

```
Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4 
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
```

## Solution

PreOrder Traversal 檢查左右兩邊的樹，有沒有 SubTree，再檢查左右兩邊的subtree是否相等。

isSubTree
- 如果 root == null 代表該值為空不會有subtree return false
- 如果 haveSameValue(l1, l2) is true，代表該node下方的subtree是相等的 return true
- Divide and Conquer 交給 root.left 和 root.right 跟 subtree 做比較。

haveSameValue
- 如果 兩棵樹都是 null return true
- 如果 只有其中一個 == null ，代表不相等，或是current value 不相等，都直接 return false
- 再丟給左和右subtree檢查，只要有一個不相等，就return false，兩個都相等才return true

```java
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null) return false;
        if(haveSameValue(s, t)) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t); // 左右皆有可能含有 Subtree
    }
    
    public boolean haveSameValue(TreeNode s, TreeNode t) {
        if(s == null && t == null) return true;
        if(s == null || t == null) return false;
        if(s.val != t.val) return false;
        return haveSameValue(s.left, t.left) && haveSameValue(s.right, t.right); // 左與右的值要相同，才能構成 Subtree
    }
}
```