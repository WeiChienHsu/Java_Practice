# Unique Path

```
Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28
```

## Dynamic Programming

利用之前的解來推敲後面的解答。

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] matrix = new int[m][n];
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // 只有上和左來決定現在的Step
                // 如果是邊邊的話，直接給定1
                matrix[i][j] = i == 0 || j == 0? 1 : matrix[i - 1][j] + matrix[i][j - 1];
            }
        }
        return matrix[m - 1][n - 1];
    }
}
```
***

# Unique Path II

不能再使用 1 來初始化上面與左邊的step，需要判斷是否在邊界中有出現障礙物，那之後的點將無法到達。

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
            
        int[][] record = new int[row][col];
        
        boolean colhasObs = false;
        boolean rowhasObs = false;
        
        for(int i = 0; i < row; i++) {
            if(obstacleGrid[i][0] == 1) {
                colhasObs = true;  
            } 
            
            if(colhasObs) {
                record[i][0] = 0;
            } else {
                record[i][0] = 1;
            }
        }
        
        for(int i = 0; i < col; i++) {
            if(obstacleGrid[0][i] == 1) {
                rowhasObs = true;  
            } 
            
            if(rowhasObs) {
                record[0][i] = 0;
            } else {
                record[0][i] = 1;
            }
        }
        
        for(int i = 1; i < row; i++) {
            for(int j = 1; j < col; j++) {
                if(obstacleGrid[i][j] == 1) {
                    record[i][j] = 0;
                    continue;
                }             
                record[i][j] = record[i - 1][j] + record[i][j - 1];
            }
        }
        return record[row-1][col-1];
    }
}
```

## Solution DP

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1)
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] += dp[j - 1];
            }
        }
        return dp[width - 1];
    }
}
```