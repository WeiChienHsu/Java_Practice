class Solution {
  public int missingNumber(int[] nums) {
      int x = 0;
      for(int i = 1; i <= nums.length; i++) {
          x = x ^ i ^ nums[i - 1];
          // i -> 1, 2, 3
          // nums[i-1] -> 1 0 3
          // x -> 0 -> 3 -> 2-> 2
      }
      return x;
  }
}