class Solution {
  public boolean canJump(int[] nums) {
      int curCanReach = 0;
      for(int i = 0; i < nums.length; i++) {
          if(i > curCanReach) return false;
          curCanReach = Math.max(curCanReach, i + nums[i]);
      }
      return true;
  }
}