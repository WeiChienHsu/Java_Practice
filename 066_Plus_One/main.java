public static int[] plusOne(int[] nums) {
  for(int i = nums.length -1 ; i >= 0; i--) {
      if(nums[i] < 9) {
          nums[i] = nums[i] + 1;
          break;
      } else {
          nums[i] = 0;
      }
  }

  if(nums[0] == 0) {
      int[] result = new int[nums.length + 1];
      result[0] = 1;
      return result;
  }
  return nums;
}