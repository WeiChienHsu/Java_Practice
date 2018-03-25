class Solution {
  public int removeDuplicates(int[] nums) {
      int i = 0;
      int j = 0;
      int count = 0;
      
      while(i < nums.length) {
          if(nums[i] != nums[j]) {
              j = i;
          }
          
          if(i - j <= 1 && nums[i] == nums[j]) {
              nums[count] = nums[i];
              count++;
          }
          i++;
      }
      return count;
  }
}