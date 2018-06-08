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
  public TreeNode constructMaximumBinaryTree(int[] nums) {
      if(nums.length == 0) return null;
      return findMaxAndBuildTree(nums, 0, nums.length - 1);
  }
  
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
}