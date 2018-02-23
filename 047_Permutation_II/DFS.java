class Solution {
  public List<List<Integer>> permuteUnique(int[] nums) {
      List<List<Integer>> results = new ArrayList<>();
      if(nums == null || nums.length == 0) {
          return results;
      }
      List<Integer> permutation = new ArrayList<>();
      boolean[] visited = new boolean[nums.length];
      dfsHelper(results, permutation, visited, nums);
      return results;
  }
  
  public void dfsHelper(List<List<Integer>> results, List<Integer> permutation, boolean[] visited, int[] nums){
      
      if(permutation.size() == nums.length){
          results.add(new ArrayList<>(permutation));
          return;
      }
      
      for(int i = 0; i < nums.length; i++) {
          if(visited[i]){
              continue;
          }
          
          if(i != 0 && nums[i] == nums[i - 1] && !visited[i-1]){
              continue;
          }
          
          permutation.add(nums[i]);
          visited[i] = !visited[i];
          dfsHelper(results, permutation, visited, nums);
          visited[i] = !visited[i];
          permutation.remove(permutation.size() - 1);
      }
  }
}