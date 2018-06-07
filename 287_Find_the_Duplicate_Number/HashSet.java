class Solution {
  public int findDuplicate(int[] nums) {
      Set<Integer> set = new HashSet<>();
      for(int i = 0; i < nums.length; i++) {
          if(!set.contains(nums[i])) {
              set.add(nums[i]);
          } else {
              return nums[i];
          }
      }
      return nums[0];
  }
}