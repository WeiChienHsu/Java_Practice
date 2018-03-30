class Solution {
  public int result = 0;
  
  public int findTargetSumWays(int[] nums, int S) {
      if(nums == null) {
          return 0;
      }
      
      dfsHelper(nums, 0, 0, S);
      return result;
  }
  
  public void dfsHelper(int[] nums, int pos, int curValue, int target) {
      if(pos == nums.length) {
          if(curValue == target){
              result++;
          }
          return;
      }
      dfsHelper(nums, pos + 1, curValue + nums[pos], target);
      dfsHelper(nums, pos + 1, curValue - nums[pos], target);
  }
}