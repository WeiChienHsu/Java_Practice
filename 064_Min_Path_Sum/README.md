# Min Path Sum
```
[[1,3,1],
 [1,5,1],
 [4,2,1]]
 ```
 - Return 1 + 3 + 1 + 1 + 1 = 7

## Solution - DFS(Buttom UP!)
- Go down and go right
```
            1
         /     \
        1       3
       / \     / \ 
      4   5   5   1
      |  / \  |\  |
      2 2   1 1 2 1
      | |   | | | |
            1
```
- Recursion : 紀錄右邊與下方
- int down = matrix[i+1][j]
- int left = matrix[i][j+1]
- 條件當 row / col  < 邊界
- 回傳上層最小值 + 當層值。
- 最終抵達右下方時，回傳該值 
- Time : O(2 ^ (mn)) 

```java
class Solution {
  public int minPathSum(int[][] grid) {
      if(grid.length == 0) return 0;
      if(grid[0].length == 0) return 0;
      return helper(grid, 0, 0);
  }
  
  public int helper(int[][] grid, int i, int j) {
      if(i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
      
      if()
      int down = Integer.MAX_VALUE;
      int right = Integer.MAX_VALUE;
      
      if(i < grid.length - 1){
          down = helper(grid, i + 1, j);
      }
      
      if(j < grid[0].length - 1) {
          right = helper(grid, i, j + 1);
      }
      
      
      return grid[i][j] + Math.min(right, down);
  }
}
```

## Memorized Search
- 記錄下來 每一層 該回傳的最小值！
```
            1
         /     \
        1       3
       / \     / \ 
      4   5   5   1
      |  / \  |\  |
      2 2   1 1 2 1
      | |   | | | |
            1
```
- 倒數第二層的 2  和 1 都是回傳相同的值。
- Time: O(mn)
- Space : O(mn)
```java
class Solution {
    public int minPathSum(int[][] grid) {
        if(grid.length == 0) return 0;
        if(grid[0].length == 0) return 0;
        int min[][] = new int[grid.length][grid[0].length];
        return helper(grid, 0, 0, min);
    }
    
    public int helper(int[][] grid, int i, int j, int[][] min) {
        if(i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
        
        if(min[i][j] != 0) {
            return min[i][j];
        }
        int down = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        
        if(i < grid.length - 1){
            down = helper(grid, i + 1, j, min);
        }
        
        if(j < grid[0].length - 1) {
            right = helper(grid, i, j + 1, min);
        }
        
        min[i][j] = grid[i][j] + Math.min(right, down);
        return min[i][j];
    }
}
```

## DP (Up to Down)
- helper(i,j) = Min(helper(i+1,j) , helper(i, j+1)) + grid[i][j])
```
                   1
                 /     \
min[1][0] = 1   1       3   min[0][1]
               / \     / \ 
              4   5   5   1   min[1][1] = Min(min[1][0],min[0][1]) + matrix[1][1]
              |  / \  |\  |     
              2 2   1 1 2 1
              | |   | | | |
                   1
```
- Induction Rule : Init 初始化
```java
min[0][0] = grid[0][0];
for(int i = 1; i < col; i++){
  for(int j = 1; j < row; j++) {
    min[i][j] = Math.min(min[i][j-1], min[i-1][j]) + gird[i][j];
  }
}
```
- 空間優化，先存下第一行
```
[1,4,5]
[1 + 1 , min(matrix[1][1] + min[1][0], matrix[1][1] + min[0][1]), 

min(matrix[1][2] + min[1][1], matrix[1][2] + min[0][2])]
```

- min[i][j] = Math.min(grid[i][j] + min[i][j-1], grid[i][j] + min[i-1][j]) 
```java
class Solution {
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        
        if(grid.length == 0) return 0;
        if(grid[0].length == 0) return 0;
        int[][] min = new int[row][col];
    // Init
        min[0][0] = grid[0][0];
        for(int i = 1; i < col; i++){
            min[0][i] = grid[0][i] + min[0][i-1] ;
        }
        
        for(int i = 1; i < row; i++){
            for(int j = 0; j < col; j++) {
                if(j == 0) {
                    min[i][j] = grid[i][j] + min[i-1][j];
                } else {
                    min[i][j] = Math.min(grid[i][j] + min[i][j-1], grid[i][j] + min[i-1][j]);
                }
            }
        }
        return min[row - 1][col - 1];   
    }
    //min[i][j] = Math.min(grid[i][j] + min[i][j-1], grid[i][j] + min[i-1][j]) 
}
```