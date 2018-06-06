class Solution {
  public int[] searchRange(int[] nums, int target) {
      if(nums.length == 0) return new int[]{-1, -1};
      
      int start = Solution.findIndexOfFirstGreaterOrEqual(nums, target);
      
      if(nums[start] != target || start == nums.length) return new int[]{-1, -1};
      int end = Solution.findIndexOfFirstGreaterOrEqual(nums, target + 1);
      
      // 如果找到的是 > target 的數字，要--，如果找到剛好一樣大的，要直接return
      return new int[]{start, nums[end] == target? end : end - 1};
  }
  
  // 找尋第一個大於等於Target的Index
  public static int findIndexOfFirstGreaterOrEqual(int[] nums, int target) {
      int start = 0, end = nums.length - 1;
      while(start + 1 < end) {
          int mid = start + (end - start) / 2;
          if(nums[mid] >= target) {
              end = mid;
          } else {
              start = mid;
          }
      }   
      return nums[start] == target? start : end;
  }
}