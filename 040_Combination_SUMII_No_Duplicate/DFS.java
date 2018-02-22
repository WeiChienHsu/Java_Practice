class Solution {
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
      List<List<Integer>> results = new ArrayList<>();
      if(candidates == null) {
          return results;
      }
      Arrays.sort(candidates);
      List<Integer> combination = new ArrayList<>();
      dfsHelper(combination, 0, target, candidates, results);
      return results;
  }
  
  private void dfsHelper(List<Integer> combination, 
                          int startIndex, 
                          int remainTarget, 
                          int[] candidates, 
                          List<List<Integer>> results) {
      
      if(remainTarget == 0) {
          results.add(new ArrayList<>(combination));
          return;
      }
      
      for(int i = startIndex ; i < candidates.length; i++ ) {
          
          if(startIndex != i && candidates[i] == candidates[i-1]) {
              continue;
          }
          
          if(remainTarget < candidates[i]) {
              break;
          }
          
          combination.add(candidates[i]);
          dfsHelper(combination, i + 1, remainTarget - candidates[i], candidates, results );
          combination.remove(combination.size() - 1);
          
      }
  }
}