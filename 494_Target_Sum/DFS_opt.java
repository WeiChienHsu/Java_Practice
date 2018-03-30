class Solution {
  public int result = 0;
  
  public int findTargetSumWays(int[] nums, int S) {
      if(nums == null) return 0;
      
      int n = nums.length;
      int[] sums = new int[n];
      
      sums[n - 1] = nums[n - 1];
      for(int i = n - 2; i >= 0; i--) {
          sums[i] = sums[i + 1] + nums[i];
      }
      
      dfsHelper(nums, sums, S, 0);
      return result;
  
  }
  
  public void dfsHelper(int[] nums, int[] sums, int remainTarget, int pos) {
      if(nums.length == pos) {
          if(remainTarget == 0) {
              result++;
          }
          return;
      }
      
      // The Largest possible value is smaller than remainTarget
      if(Math.abs(remainTarget) > sums[pos]) return;
      
      dfsHelper(nums, sums, remainTarget + nums[pos], pos + 1);
      dfsHelper(nums, sums, remainTarget - nums[pos], pos + 1);
      
  }
}