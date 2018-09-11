/* 1. Fine the first index of number that was not in a incresement order from the last element
   2. Then, search the nearset larger number from that n to the last element
   3. Swap this two number
   4. If the whole array is sorted in a incresement order, revers whole array.
*/

class Solution {
  public void nextPermutation(int[] nums) {
      
      if(nums.length <= 1) return; 
      
      int index = -1;
      /* Fine the first index not in incresement order */
      for(int i = nums.length - 1; i > 0; i--) {
          if(nums[i] > nums[i - 1]) {
              index = i - 1;
              break;
          }
      }
      
          
      if(index == -1) {
          reverse(nums, 0, nums.length - 1);
          return;
      }
      
      /* Find the nearest element larger than nums[index] */
      int nearestIndex = -1;
      int diff = Integer.MAX_VALUE;
      
      for(int i = index + 1; i < nums.length; i++) {
          if(nums[i] > nums[index] && (nums[i] - nums[index] <= diff)) {
              diff = nums[i] - nums[index];
              nearestIndex = i;
          }
      }
      
      swap(nums, nearestIndex, index); 
      /* Recerse the rest of the element in the array */
      reverse(nums, index + 1, nums.length - 1);
      
  }
  
  public void swap(int[] nums, int i, int j) {
      int temp = nums[i];
      nums[i] = nums[j];
      nums[j] = temp;
  }
  
  public void reverse(int[] nums, int start, int end) {
      for(int i = start, j = end; i < j; i++, j--) {
          swap(nums, i, j);
      }
  }
}