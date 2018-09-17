
/*
  Buttom-up Solution
  1. Initilze a new array to record the currnt Min Options.class
  2. Traversal the Trangle in a level method.
  3. Get the min value between A[j] & A[j + 1] and plus the current value in that level.
*/

class Solution {
  public int minimumTotal(List<List<Integer>> triangle) {
      int[] minValue = new int[triangle.size() + 1];
      
      /* [4, 1, 8, 3]             Level 3    */  
      /* [6 + 1, 5 + 1, 7 + 3, 3] Level 2    */
      /* [3 + 6, 4 + 6 , 10, 3]   Level 1    */
      /* [2 + 9, 10, 10, 3]       Level 0    */
      /* Return 2                            */
      
      for(int i = triangle.size() - 1; i >= 0; i--) {
          for(int j = 0; j < triangle.get(i).size(); j++) {
              minValue[j] = Math.min(minValue[j], minValue[j + 1]) + triangle.get(i).get(j);
          }
      }
      return minValue[0];
  }
}