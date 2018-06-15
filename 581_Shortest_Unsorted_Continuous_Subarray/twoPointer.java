class Solution {
  public int findUnsortedSubarray(int[] nums) {
      int[] sortedNums = nums.clone();
      Arrays.sort(sortedNums);
      // [2, 3, 4, 5, 6]
      // [2, 5, 3, 4, 6]
      
      int start = 0;
      int end = nums.length - 1;
      
      while(start < end && nums[start] == sortedNums[start]) start++;
      if (start == end) return 0;
      
      while(end > 0 && nums[end] == sortedNums[end]) end--;
      
      return end - start + 1;
  }
}