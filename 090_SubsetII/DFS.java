class Solution {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
      List<List<Integer>> result = new ArrayList<>();
      List<Integer> combination = new ArrayList<>();
      result.add(new ArrayList<>(combination));
      if(nums == null) return result;
      
      Arrays.sort(nums);
      dfsHelper(nums, 0, result, combination);
      return result;
  }
  
  public static void dfsHelper(int[] nums, int start, List<List<Integer>> result, List<Integer> combination) {
      
      if(!result.contains(combination)) {
          result.add(new ArrayList<>(combination));
      }
      
      for(int i = start; i < nums.length; i++) {
          combination.add(nums[i]);
          dfsHelper(nums, i + 1, result, combination);
          combination.remove(combination.size() - 1);
      }
  }
}