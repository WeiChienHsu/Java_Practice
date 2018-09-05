# Paint House

```java
/* 
        思路: 一道很明显的动态规划的题目. 每个房子有三种染色方案, 那么如果当前房子染红色的话, 
        最小代价将是上一个房子的绿色和蓝色的最小代价+当前房子染红色的代价. 对另外两种颜色也是如此. 
        
        因此动态转移方程为: 

             dp[i][0] = min(dp[i-1][1], dp[i-1][2]) + costs[i-1][0];

             dp[i][1] = min(dp[i-1][0], dp[i-1][2]) + costs[i-1][1];

             dp[i][2] = min(dp[i-1][0], dp[i-1][1]) + costs[i-1][2];
*/

class Solution {
    public int minCost(int[][] costs) {
        int[][] dp = new int[costs.length + 1][3];
        dp[0][0] = dp[0][1] = dp[0][2] = 0;
        
        for(int i = 1; i < costs.length + 1; i++) {
            int[] cost = costs[i - 1];
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[2];
        }
        
        return Math.min(Math.min(dp[costs.length][0], dp[costs.length][1]), dp[costs.length][2]);
        
    }

}
```