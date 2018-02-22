class Solution {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
      List<List<Integer>> results = new ArrayList<>();
      if(candidates == null) {
          return results;
      }    
      
      Arrays.sort(candidates);
      List<Integer> combination = new ArrayList<>();
      dfsHelper(results, combination, 0, target, candidates);
      return results;
      
      }
  
  public void dfsHelper(List<List<Integer>> results,
                  List<Integer> combination,
                  int startIndex,
                  int remainTarget,
                  int[] candidates) {
      // 出口
      if(remainTarget == 0) {
          results.add(new ArrayList<Integer>(combination));
          return;
      }
      
      
      for(int i = startIndex; i < candidates.length; i++) {
          
          // 之後的數字都比剩餘的Target大了，直接跳出for循環
          if(remainTarget < candidates[i]) {
              break;
          }
          
          // 掉過重複的數字
          if(i != 0 && candidates[i] == candidates[i-1]) {
              continue;
          }
          
          combination.add(candidates[i]);
          dfsHelper(results, combination, i, remainTarget - candidates[i], candidates);
          combination.remove(combination.size() - 1);
      }
  }
  }
