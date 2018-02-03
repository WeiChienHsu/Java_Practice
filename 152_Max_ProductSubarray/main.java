class Solution {
  public int maxProduct(int[] nums) {
      if(nums.length == 1) return nums[0];
      int maxSoFar = nums[0];
      int curMax = nums[0];
      int curMin = nums[0];
      
      for(int i = 1; i < nums.length; i++){
          int temp = curMax;
          curMax = Math.max(Math.max(curMax * nums[i], nums[i]), curMin * nums[i]);
          curMin = Math.min(Math.min(curMin * nums[i], nums[i]), temp * nums[i]);
          maxSoFar = Math.max(maxSoFar, curMax);
      }
      return maxSoFar;
  }
}