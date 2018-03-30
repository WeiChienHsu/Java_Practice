# 106. Construct Binary Tree from Inorder and Postorder Traversal

Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
```
For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
```

## Solution
- Put All Nodes and their index into a Map from inorder traversal since we need to seperate the left and right side from inorder array
- Find the Root in the end of PostOrder Array
- Find the index of that number in inorder Map, left side are the left children and right side are the rigth children
- Get the rightSum by root index in the inorder Map ( inorderEnd - inorderRoot)
- Connect root.rigth by DFS helper by giving the start and end pointer in both Postorder array and Inorder Map
- Right: Post -> postEnd - rightSum, PostEnd - 1  || Inorder -> inorderRoot + 1, InorderEnd
- Left: Post -> postStart, postEnd - rightSum - 1 || Inorder -> InorderStart, inorderRoot - 1 
- Exist: When Start Index > end


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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder.length != postorder.length) {
            return null;
        }
        
        Map<Integer, Integer> inorderMap = new HashMap<>();
        
        for(int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        
        TreeNode root = buildTree(postorder, 0, postorder.length - 1, inorderMap, 0, inorder.length - 1);
        return root;
    }
    
    public TreeNode buildTree(int[] postorder, int startPost, int endPost,
                     Map<Integer, Integer> inorderMap, int startIn, int endIn) {
        if(startPost > endPost || startIn > endIn) {
            return null;
        }
        
        TreeNode root = new TreeNode(postorder[endPost]);
        int rootIn = inorderMap.get(root.val);
        int rightNum = endIn - rootIn;
        
        root.right = buildTree(postorder, endPost - rightNum, endPost - 1, inorderMap, rootIn + 1, endIn);
        root.left = buildTree(postorder, startPost, endPost - rightNum - 1, inorderMap, startIn, rootIn - 1);
        
        return root;
    }
}

```