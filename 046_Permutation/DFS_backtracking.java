class Solution {
  public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> results = new ArrayList<>();
      if(nums == null || nums.length == 0) {
          return results;
      }
      
      List<Integer> permutation = new ArrayList<>();
      
      dfsHelper(results, permutation, nums);
      return results;
  }
  
  private void dfsHelper(List<List<Integer>> results, List<Integer> permutation, int[] nums) {
      
      if(permutation.size() == nums.length) {
          results.add(new ArrayList<>(permutation));
          return;
      }
      
      for(int i = 0; i < nums.length; i++) {
          
          if(permutation.contains(nums[i])) {
              continue;
          }
          
          permutation.add(nums[i]);
          dfsHelper(results, permutation, nums);
          permutation.remove(permutation.size() - 1);
      }
  }
}