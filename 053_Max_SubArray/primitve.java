public static int maxSubArray(int[] nums) {
  int maxNum = 0;
  for(int i = 0; i < nums.length; i++){
      int num = 0;
      for(int j = i ; j < nums.length ; j++) {
          num += nums[j];
          maxNum = Math.max(maxNum, num);
      }
  }
  return maxNum;
}