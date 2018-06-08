/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

    // 要將 LeftMost 的 Index 紀錄在一個 Array 內， 用 Level 當作查詢的 Index (Which means level 從 0 開始)
    // 樹可以像Matrix一樣，轉換成 index 的方式呈現， root = index, root.left = 2 * index, root.right = 2 * index + 1
    // 比較current root的index與"當層"的leftMost index值，紀錄在一個 int[1] 裡面 
    //(因為array是pass by reference，所以可以跟著helper function 傳入下層)
    // 處理完當層之後，處理下一層的left與right，帶入 level + 1，以及各自的index。
    class Solution {
      public int widthOfBinaryTree(TreeNode root) {
          List<Integer> leftMost = new ArrayList<>();
          int[] max = new int[1];
          max[0] = 0;
          
          if(root == null) return max[0];
          Solution.helper(root, leftMost, 0, 1, max);
          return max[0]; 
      }
      
      public static void helper(TreeNode root, List<Integer> leftMost, int level, int currentIndex, int[] max) {
          if(root == null) return;
          
          // Add the Current Level Left Most Index into the array
          if(level == leftMost.size()) {
              leftMost.add(currentIndex);
          }
          
          // Compare the distance with the max width
          int distance = currentIndex - leftMost.get(level) + 1;
          max[0] = Math.max(max[0], distance);
          
          // DFS left and right child node with their own index
          Solution.helper(root.left, leftMost, level + 1, currentIndex * 2, max);
          Solution.helper(root.right, leftMost, level + 1, currentIndex * 2 + 1, max);   
      }
  }