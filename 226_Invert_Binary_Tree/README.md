# Invert Binary Tree

- 暫存右子樹
- 右邊接上左邊反轉完成的樹
- 左邊接上暫存右邊反轉完成的樹
```java
    public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return null;
        }
        
        TreeNode tempRight = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(tempRight);
        return root;
    }
}
```