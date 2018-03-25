# 01 Matrix

Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.
```
Example 1: 
Input:

0 0 0
0 1 0
0 0 0
Output:
0 0 0
0 1 0
0 0 0
```
```
Example 2: 
Input:

0 0 0
0 1 0
1 1 1
Output:
0 0 0
0 1 0
1 2 1
```

## Solution
- 使用一個Queue來解題，先將所有0放入其中，其他點以MAX當作該點的數值
- BFS Queue中的所有點，如果四周的點符合邊界要求，並且大於該點，我們要更新周圍的點（該點+1），並將周圍點的放入Queue中繼續處理。

- Used a Queue to Record un-porcessing node
- Put all 0 into Queue, and MAX_Value into other nodes
- BFS All nodes in Queue:
- Used a helper funtion to check 4 directions
- If valid and new node is larger then curent node
- Update the new Node value by adding one from current node
- Put that node into Queue to process
- Time Complexity : O(M*N) 

```java
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        Deque<int[]> queue = new ArrayDeque<>();
        int row = matrix.length;
        int col = matrix[0].length;
        
        int[][] helper = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        // Update matrix and Queue
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    continue;
                } 
                matrix[i][j] = Integer.MAX_VALUE;
            }
        }
        
        while(!queue.isEmpty()) {
            int[] cur = queue.pollFirst();
            int curRow = cur[0];
            int curCol = cur[1];
            for(int[] dir : helper) {
                int newRow = curRow + dir[0];
                int newCol = curCol + dir[1];
                
                if( isValid(newRow, newCol, row, col) && 
                   matrix[newRow][newCol] > matrix[curRow][curCol] + 1) {
                    queue.offerLast(new int[]{newRow, newCol});
                    matrix[newRow][newCol] = matrix[curRow][curCol] + 1;
                }

            }
        }
        
        return matrix;
    }
    
    public boolean isValid(int newRow, int newCol, int row, int col){
        return newRow >= 0 && newCol >= 0 && newRow < row && newCol < col;
    }
}
```