class Solution {
  public List<Integer> findDisappearedNumbers(int[] nums) {
      List<Integer> list = new ArrayList<>();
      int left = 0;
      
      while(left < nums.length) {
          /* number in the right position */
         if(nums[left] == left + 1) {
             left ++;
         } else {
             if(nums[nums[left] - 1] == nums[left]) {
                 /* the target position has existed a right number which is duplicated */
                 left++;
             } else {
                 int temp = nums[left];
                 nums[left] = nums[nums[left] - 1];
                 nums[temp - 1] = temp; /* Remember we have change the nums[left] */ 
             }
         }
      }
      
      for(int i = 0; i < nums.length; i++) {
          if(nums[i] != i + 1) list.add(i + 1);
      }
      
      return list;
      
      
  }
}