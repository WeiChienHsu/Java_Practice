class Solution {
  public int findMin(int[] nums) {
      if(nums.length == 1) {
         return nums[0]; 
      }
      
      int start = 0;
      int end = nums.length - 1;
      while(start + 1 < end) {
          int mid = start + (end - start) / 2;
          if(nums[mid] == nums[end]) {
              end = mid;
          } else if (nums[mid] < nums[end]) {
              end = mid;
          } else {
              start = mid;
          }
      }
      
      return (nums[end] > nums[start])? nums[start] : nums[end];
  }
}