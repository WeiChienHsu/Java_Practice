class Solution {
  public int findMin(int[] nums) {
      int start = 0;
      int end = nums.length - 1;
      
      while(start + 1 < end) {
          int mid = start + (end - start) / 2;
          // Dedupe
          if(nums[mid] == nums[end]) {
              end--;
          } else if(nums[mid] == nums[start]){
              start++;
          // Left Side is Larger
          } else if(nums[mid] > nums[start] && nums[mid] < nums[end]) {
              end = mid;
          // Right Side is Larger
          } else if(nums[mid] > nums[end] && nums[mid] < nums[start]) {
              start = mid;
          // Mid is the largest
          } else if(nums[mid] > nums[end] && nums[mid] > nums[start]) {
              if(nums[start] >= nums[end]) {
                  start = mid;
              } else {
                  end = mid;
              }
          // Mid is the Smallest
          } else if(nums[mid] < nums[end] && nums[mid] < nums[start] ){
              if(nums[start] >= nums[end]) {
                  end = mid;
              } else {
                  start = mid;
              }
          }
      }
      return nums[start] > nums[end]? nums[end] : nums[start];
  }
}