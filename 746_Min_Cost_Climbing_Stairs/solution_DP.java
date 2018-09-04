/* 
    DP formula:
    total[0] = cost[0]
    total[1] = cost[1]
    total[2] = MIN((total[2 - 1] + cost[2]), (total[2 - 2] + cost[2]))
    
    -> total[n] = Math.min(total[n - 1] + cost[n], total[n - 2] + cost[n]);
*/


class Solution {
  public int minCostClimbingStairs(int[] cost) {
      
      int[] total = new int[cost.length + 1];
      total[0] = cost[0];
      total[1] = cost[1];
      
      for(int i = 2; i < cost.length; i++) {
          total[i] = Math.min((total[i - 1] + cost[i]), (total[i - 2] + cost[i]));
      }
      
      return Math.min(total[cost.length - 1], total[cost.length - 2]);
  }
}