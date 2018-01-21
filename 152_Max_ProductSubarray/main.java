public static int maxProduct(int[] nums) {
  int max = nums[0];
  int min = nums[0];
  int result = Integer.MIN_VALUE;
  for(int i = 1; i < nums.length; i++) {
      int temp = max;
      max = Math.max(Math.max(max * nums[i], min * nums[i]) , nums[i]);
      min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
      result = Math.max(result, max);
  }
  return result;
}