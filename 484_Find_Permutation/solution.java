class Solution {
  public int[] findPermutation(String s) {
      int[] nums = new int[s.length() + 1];
      
      for(int i = 1; i <= s.length() + 1; i++) {
          nums[i - 1] = i;
      }
      
      int lastStart = 0;
      int current = 0;
      
      for(char c : s.toCharArray()) {
          if(c == 'D') {
              swap(nums, lastStart, current + 1);
          } else {
              lastStart = current + 1;
          }
          
          current++;
      }
      return nums;
  }
  
  public void swap(int[] nums, int start, int target) {
      int temp = nums[target];
      for(int i = target; i > start; i--) {
          nums[i] = nums[i - 1];
      }
      nums[start] = temp;
  }
}