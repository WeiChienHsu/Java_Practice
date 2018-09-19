/* 
    Maintains:
        - tempList
        - currentIndex: record the current element

    Steps:
        - Sort the original integer array
        - Remainder < 0: The total sum is too large, return
        - Remainder == 0: Find the combination, add into the result
        - Remainder > 0: Still need to find the number, check if the current Index of value is larger than remainder
        
    Base case:
        - Remainder < 0
*/


class Solution {
  public List<List<Integer>> combinationSum3(int k, int n) {
      List<List<Integer>> results = new ArrayList<>();
      int[] candidates = new int[9];
      for(int i = 1; i < 10; i++) {
          candidates[i - 1] = i;
      }
      
      backtracking(results, candidates, n, 0, new ArrayList<>(), k);
      return results;
      
  }
  
  public void backtracking(List<List<Integer>> results, int[] nums, int remainder, int currentIndex, List<Integer> tempList, int k) {
      if(remainder < 0) return;
      else if(remainder == 0) {
          if(results.contains(tempList) || tempList.size() != k) return;
          results.add(new ArrayList<>(tempList)); 
          
      } else {
          for(int i = currentIndex; i < nums.length; i++) {
              if(nums[i] > remainder) break;
              tempList.add(nums[i]);
              backtracking(results, nums, remainder - nums[i], i + 1, tempList, k);
              tempList.remove(tempList.size() - 1);
          }
      }
  }
}