# Construct Binary Tree from Preorder and Inorder Traversal
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
```
For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
```

## Solution
透過preOder找Root，用Root透過inOrder找到Left和Right，透過Left長度找到左右節點的newRoot，重複不斷找Root的工作，直到Start > End得到新樹。

- Preorder的第一個值，必定為 Root
- 在InorderMap中找到該Root，以左的值必定為 Left ， 以右必定為 Right (因為Inorder先走完Left Child才會到Root)
- 有了左邊的長度，就可以找到Preorder中Left和Right的新Root：
- 可以先把inOrder切分成Left(inStart, inStart, inRoot) / Right(inRoot + 1, inEnd)
- 求得左邊子樹的個數 = inRoot - inStart
- preOrder也被切成Left(preStart + 1, preStart + leftNum) / Right(preStart + leftNum, preEnd)
- root.left -> 新的Root是preStart + 1 
- root.right -> 新的Root是 preStart + leftNum + 1
- 每次重複操作定義root和其left and right，直到 start > end 的時候return null

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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length != inorder.length) return null;    
    
    
        Map<Integer, Integer> inorderMap = new HashMap<>();
    
        for(int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
    
        TreeNode root = buildTree(preorder, 0, preorder.length - 1, 0, inorder.length - 1, inorderMap);
            return root;
        }
    
    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, 
                              int inStart, int inEnd, Map<Integer, Integer> inorderMap) {
        
        if(preStart > preEnd || inStart > inEnd) return null;
        
        // preorder = [3,9,20,15,7]
        // inorder = [9,3,15,20,7]
        
        TreeNode root = new TreeNode(preorder[preStart]); //[3]
        
        // find the index of root in inOrder
        // find leftNum in child tree
        int inRoot = inorderMap.get(preorder[preStart]);
        int leftNum = inRoot - inStart;
        
        root.left = buildTree(preorder, preStart + 1, preStart + leftNum, inStart, inRoot - 1, inorderMap); //[9]
        root.right = buildTree(preorder, preStart + leftNum + 1, preEnd, inRoot + 1, inEnd, inorderMap); //[20,15,7]
        
        return root;
        
    }
}
```