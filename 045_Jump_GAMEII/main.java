class Solution {
  public int jump(int[] nums) {
      if(nums == null || nums.length == 0) return 0;
      int[] min = new int[nums.length];
      
      min[0] = 0;
      int curIndex = 0;

      for(int i = 1; i < nums.length ; i++) {
          for(int j = curIndex; j < i; j++ ) {
              if(nums[j] + j >= i) {
                  // 更新每個點的最短Steps
                  min[i] = min[j] + 1;
                  curIndex = j;
                  break;
              }
          }
      }
      return min[nums.length -1]; 
  }
}