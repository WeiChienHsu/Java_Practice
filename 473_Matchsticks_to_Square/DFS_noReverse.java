class Solution {
  public boolean makesquare(int[] nums) {
      if(nums == null || nums.length < 4) return false;
      int sum = 0;
      for(int num : nums) sum += num;
      if(sum % 4 != 0) return false;
      
      int[] edges = new int[4];
      return dfs(nums, edges, nums.length - 1, sum / 4);
  }
  
  private boolean dfs(int[] nums, int[] edges, int index, int target) {
      if(index < 0) {
          return true;
      }
      
      for(int i = 0; i < edges.length; i++) {
          edges[i] += nums[index];
          if(edges[i] <= target) {
              if(dfs(nums, edges, index - 1, target)){
                  return true;
              }
          }
         edges[i] -= nums[index]; 
      }
      return false;
  }
}