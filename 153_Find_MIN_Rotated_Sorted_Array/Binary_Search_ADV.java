class Solution {
  public int findMin(int[] nums) {
      int start = 0;
      int end = nums.length - 1;
      
      while(start + 1 < end) {
          int mid = start + (end - start) / 2;
          // When mid is the largest
          if(nums[mid] > nums[start] && nums[mid] > nums[end]) {
              if(nums[start] > nums[end]) {
                  start = mid;
              } else {
                  end = mid;
              }
          // When mid is the smallest    
          } else if(nums[mid] < nums[start] && nums[mid] < nums[end]){
              if(nums[start] > nums[end]) {
                  end = mid;
              } else {
                  start = mid;
              }
          } else if(nums[mid] > nums[start] && nums[mid] < nums[end]) {
              end = mid;
          } else if(nums[mid] > nums[end] && nums[mid] < nums[start]) {
              start = mid;
          } 
      }
      return nums[start] > nums[end]? nums[end] : nums[start];
  }
}