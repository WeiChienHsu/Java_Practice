/* 
    Maintains 2 variables:
        - current n: 
        - startFatctor:
        - tempList:
    
    Base case:
        - when the n <= 1 we need to check if tempList have more than one elements
        - if YES, add the tempList into result, if NO, just return the method
    
    Steps:
        - Keep try number from 2. if(n % i == 0) means that the number i is the factory of n 
        - Diveided n by 2 to get the next number.

*/


class Solution {
  public List<List<Integer>> getFactors(int n) {
      List<List<Integer>> results = new ArrayList<>();
      backtracking(results, new ArrayList<>(), n, 2);
      return results;
      
  }
  
  public void backtracking(List<List<Integer>> results, List<Integer> tempList, int n, int startFactor) {
      if(n <= 1) {
          if(tempList.size() > 1) {
              results.add(new ArrayList<>(tempList));
          }
          return;
      }
      
      for(int i = startFactor; i <= n; i++) {
          if(n % i == 0) {
              tempList.add(i);
              backtracking(results, tempList, n / i, i);
              tempList.remove(tempList.size() - 1);
          }
      }
  }
}