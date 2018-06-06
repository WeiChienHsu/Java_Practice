class Solution {
  public int lengthOfLIS(int[] nums) {
      if(nums.length == 0) return 0;
      if(nums.length == 1) return 1;
      
      int longestLength[] = new int[nums.length]; 
      
      // Init the Array with the longest Length of 1
      for(int i = 0; i < nums.length; i++) {
          longestLength[i] = 1;
      }
      
      for(int right = 1; right < nums.length; right++) {
         for(int left = 0; left < right; left++) {
             if(nums[left] < nums[right]) {
                 if(longestLength[right] <= longestLength[left]) {
                     longestLength[right]++;
                 }
             }
         } 
      }
      
      int longest = 0;
      for(int i = 0; i < nums.length; i++) {
          longest = Math.max(longest, longestLength[i]);
      }
  
      return longest;
  }
}