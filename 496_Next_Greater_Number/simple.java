class Solution {
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
      int[] results = new int[nums1.length];
      
      //[2,4] [1,2,3,4]
      // c       c n
      
      for(int i = 0; i < nums1.length ; i++){
          int curNum = nums1[i];
          int nextLargestNum = findNextLargestNumber(curNum, nums2);
          if(nextLargestNum <= curNum){
              results[i] = -1;
          } else{
              results[i] = nextLargestNum;
          }
          
      }
      return results;
  }
  // Return the next largest
  // 2 -> [1,2,3,4]
  private int findNextLargestNumber(int num, int[] nums) {
      boolean metNum = false;
      for(int i = 0; i < nums.length; i ++) {
          if(nums[i] == num) metNum = true;
          if(metNum && nums[i] > num) {
              return nums[i];
          }
      }
      return num;
  }
}