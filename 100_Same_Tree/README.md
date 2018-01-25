# Same Tree

```
Example 1:

Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true
Example 2:

Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false
```

## Solution - PreOrder Treversal
- Save Each Node into List and null as 0 as well.
```java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        List<Integer> tree1 = new ArrayList<>();
        List<Integer> tree2 = new ArrayList<>();
        treeToList(p, tree1);
        treeToList(q, tree2);
        return tree1.equals(tree2)? true : false;
    }
    
    public void treeToList(TreeNode root, List<Integer> list){
        if(root == null){
           list.add(0);
            return;
        } 
        list.add(root.val);
        treeToList(root.left, list);
        treeToList(root.right, list);   
    }
}
```

## Solution - Opt
- Dont use another space for saving those value of nodes
- Recursion: Keep checking the if those nodes are the same
```java
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val == q.val)
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        return false;
    }
```
