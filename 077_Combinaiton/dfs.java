class Solution {
  public List<List<Integer>> combine(int n, int k) {
      List<List<Integer>> result = new ArrayList<>();
      if(n == 0 && k == 0) return result;
      List<Integer> combination = new ArrayList<>();
      dfsHelper(result, 1, n, k, combination);
      return result;
  }
  
  public static void dfsHelper(List<List<Integer>> result, int start, int n, int k, List<Integer> combination) {
      
      if(combination.size() == k) {
          result.add(new ArrayList<Integer>(combination));
          return;
      }
      
      for(int i = start; i <= n; i++) {
          combination.add(i);
          dfsHelper(result, i + 1, n, k, combination);
          combination.remove(combination.size() - 1);
      }
  }
}