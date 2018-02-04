class Solution {
  public List<List<Integer>> subsets(int[] nums) {
      List<List<Integer>> res = new ArrayList<List<Integer>>();
      
      if(nums == null) {
          return res;
      }
      
      if(nums.length == 0) {
          res.add(new ArrayList<Integer>());
          return res;
      }
      
      // Sort : deduplication
      Arrays.sort(nums);
      
      List<Integer> subset = new ArrayList<>();
      dfsHelper(subset, nums, 0, res);
      return res;
  }
  
  private void dfsHelper(List<Integer> subset,
                         int[] nums,
                         int startIndex,
                         List<List<Integer>> res) {
      
      // Deep Copy (Reference)
      // 必須要 new 一個 ArrayList<Integer>(subset) : 
      // 因為 subset在 DFS 結束後回傳會是個[]，必須要new一個新的List記錄當前狀況
      
      res.add(new ArrayList<Integer>(subset));
      // O(n)
      
      for(int i = startIndex; i < nums.length ; i++) {
          // 嘗試尋找 subset + [num[i]] 開頭的所有子集
          // [] + [1] = [1] [1] + [2] = [1,2] [1,2] + [3] = [1,2,3] [1] + [3] = [1,3]
          // [] + [2] = [2] [2] + [3] = [2,3]
          // [] + [3] = [3] 
          subset.add(nums[i]);
          dfsHelper(subset, nums, i + 1, res);
          
          // Backtracking
          subset.remove(subset.size() - 1);
      }
  }
  
  
}