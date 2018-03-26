# Out of Boundary Paths

There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.



##
- 有一個 m*n 的grid，一個足球起始於(i,j)點，記錄在N步之內，可以讓球出界的方式，最終結果要 % 10^9 + 7 
- 紀錄每一步的變化，每一步發生時，要有個temp matrix，紀錄到達該點的可能步伐數
- 如果在該部內出界了，要記錄在結果當中 result = (result + count(oldNode)) % MOD 
- 如果沒有出界，要記錄在 temp(newNode) = (temp(newNode) + count(oldNode)) % MOD
- 每結束一步，更新 temp 到 count 值當中
- 起始時，改變 count[i][j] = 1 ， 其他點為 0 ，代表著該點出發時已記錄為一步

```java
class Solution {
    public int findPaths(int m, int n, int N, int a, int b) {
        
        if(N <= 0) {
            return 0;
        }
        
        final int MOD = 1000000007;
        int[][] count = new int[m][n];
        count[a][b] = 1;
        int result = 0;
        int[][] helper = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        for(int step = 0; step < N; step++ ) {
            int[][] temp = new int[m][n];
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    int curM = i;
                    int curN = j;
                    
                    for(int[] dir : helper) {
                        int newM = curM + dir[0];
                        int newN = curN + dir[1];
                        
                        if(outOfBoundry(m, n, newM, newN)) {
                            // Out of boundry -> Record new result
                            result = (result + count[curM][curN]) % MOD;
                        } else {
                            // Not out of boundry -> Record temp value
                            temp[newM][newN] = (count[curM][curN] + temp[newM][newN]) % MOD;
                        }
                    }
                }
            }
            count = temp;
        }
        return result;
    }
    
    public boolean outOfBoundry(int m, int n, int newM, int newN) {
        return newM < 0 || newN < 0 || newM >= m || newN >= n;
    }
}
```