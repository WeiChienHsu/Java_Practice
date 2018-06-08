# Max Binary Tree

Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

```
Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1

```

## Solution -> DFS
將 Order matters 的 Array 中最大的數字，轉換成一個Tree

- 先使用Helper Function 找到當時(start, end 限制) Array 內最大的數字，回傳該Index
- 利用maxIndex創造一個新的Root，將此Root.left 與 Root.right 分別連到不同的node上
- 使用 Start 與 End 來控制 subtree， maxIndex 左方的(start, maxIndex - 1) 作為left subtree，反之右方的(maxIndex + 1, end)作為right subtree
- Base case 當 Start > End 的時候，代表Max已經在邊界，直接 Return 一個 Null

```java
    public static TreeNode findMaxAndBuildTree(int[] nums, int start, int end) {
        if(start > end) return null;
        int maxIndex = Solution.findMax(nums, start, end);
        TreeNode root = new TreeNode(nums[maxIndex]);
        
        root.left = findMaxAndBuildTree(nums, start, maxIndex - 1);
        root.right = findMaxAndBuildTree(nums, maxIndex + 1, end);
        
        return root;
    }

    public static int findMax(int[] nums, int start, int end) {
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for(int i = start; i <= end; i++) {
            if(nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
```