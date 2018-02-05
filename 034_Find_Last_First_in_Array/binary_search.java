class Solution {
  public int[] searchRange(int[] nums, int target) {
      int[] result = new int[2];
      result[0] = findFirstPostion(nums, target);
      result[1] = findLastPostion(nums, target);
      return result;
  }
  
  
  public int findFirstPostion(int[] nums, int target) {
      if (nums == null || nums.length == 0) {
          return -1;
      }
      
      int start = 0;
      int end = nums.length - 1;
      
      while(start + 1 < end) {
          int mid = start + (end - start) / 2;
          if(nums[mid] == target) {
              end = mid;
          } else if (nums[mid] > target) {
              end = mid;
          } else {
              start = mid;
          }
      }
      
      
      if (nums[start] == target) {
            return start;
      }
      
      if(nums[end] == target) {
          return end;
      }
      return -1;
  }
  
  
  public int findLastPostion(int[] nums, int target) {
      if (nums == null || nums.length == 0) {
          return -1;
      }
      
      int start = 0;
      int end = nums.length - 1;
      
      while(start + 1 < end) {
          int mid = start + (end - start) / 2;
          if(nums[mid] == target) {
              start = mid;
          } else if (nums[mid] > target) {
              end = mid;
          } else {
              start = mid;
          }
      }
      
      if(nums[end] == target) {
          return end;
      }
      
      if (nums[start] == target) {
          return start;
      }
      return -1;
  }
}