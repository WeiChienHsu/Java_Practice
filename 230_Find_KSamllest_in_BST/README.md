# Find the K smallest in BST
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

## Solution
Record all nodes inorder, from the smallest to the largest and take the target out of the array.

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
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return -1;
        List<Integer> nodeLists = new ArrayList<>();
        Solution.inOrderTraversal(root, nodeLists);
        
        return nodeLists.get(k-1);
    }
    
    public static void inOrderTraversal(TreeNode root, List<Integer> nodeLists) {
        if(root == null) return;
        
        inOrderTraversal(root.left, nodeLists);
        nodeLists.add(root.val);
        inOrderTraversal(root.right, nodeLists);
    }
}
```


## Fellow Up

What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?