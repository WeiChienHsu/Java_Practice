class Solution {
  public int triangleNumber(int[] nums) {
      Arrays.sort(nums);
      int count = 0;
      for(int i = nums.length - 1;  i >= 2; i--) {
          int third = nums[i];
          int left = 0;
          int right = i - 1;
          while(left < right) {
              if(nums[left] + nums[right] > third) {
                  count += right - left;
                  right--;
              } else {
                  left++;
              }   
          }
      }
      return count;
  }
}