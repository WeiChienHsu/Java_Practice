# Surround Regions

- Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

- A region is captured by flipping all 'O's into 'X's in that surrounded region.

### For example
```
X X X X
X O O X
X X O X
X O X X
```
After running your function, the board should be:
```
X X X X
X X X X
X X X X
X O X X
```

## Solution - BFS
- 1. Give a Class of Points{int x, int y} to record the Node in Queue
- 2. Traversal all Edges to transfer all 'O' into '+' and also add them into a Queue
- 3. Now, we got all Edges with 'X' or '+'
- 4. Base on the '+' in the edges, we use Queue to do a BFS to all the '+'
- 5. Transfer those 'O' around(up, bottom, left and right) into '+' as well
- 6. How to use BFS? 
- Check the vaild (row - 1 >= 0) (row + 1 < rowN) (col - 1 >= 0) (col + 1 < colN)
- If broad[row-1] == 'O' , change it into 'X' and Add into the Queue
- 7. When the Queue is Empty, means we have already traversal all Nodes the start from edge O
- 8. Traversal all Nodes again: Change '+' into O, change 'O' into 'X'
- (Which means we couldn't change those 'O' that aren't be touched by edged O)


```java
class Solution {
    public void solve(char[][] board) {
        
        if(board.length == 0){
            return;
        }
        
        Deque<Point> queue = new ArrayDeque<>();
        int rowN = board.length;
        int colN = board[0].length;
        
        if(colN == 0) {
            return;
        }
        
        // Row Edges
        for(int i = 0; i < rowN; i++ ) {
            if(board[i][0] == 'O'){
                board[i][0] = '+';
                queue.offerLast(new Point(i, 0));
            }
            
            if(board[i][colN - 1] == 'O'){
                board[i][colN - 1] = '+';
                queue.offerLast(new Point(i, colN - 1));
            }
        }
            
        // Col Edges
        for(int j = 0; j < colN; j++) {
            if(board[0][j] == 'O'){
                board[0][j] = '+';
                queue.offerLast(new Point(0, j));
            }
            
            if(board[rowN - 1][j] == 'O'){
                board[rowN - 1][j] = '+';
                queue.offerLast(new Point(rowN - 1, j));
            }
        }
        
        // BFS all '+' in queue to change all 'O' met by '+'
        while(!queue.isEmpty()){
            
            Point cur = queue.pollFirst();
            int row = cur.x;
            int col = cur.y;
            
            // Upside
            if(row - 1 >= 0 && board[row - 1][col] == 'O'){
                board[row - 1][col] = '+';
                queue.offerLast(new Point(row - 1, col));
            }
                
            // Buttom
            if(row + 1 < rowN && board[row + 1][col] == 'O'){
                board[row + 1][col] = '+';
                queue.offerLast(new Point(row + 1, col));
            }
                
            // Left
            if(col - 1 >= 0 && board[row][col - 1] == 'O') {
                board[row][col - 1] = '+';
                queue.offerLast(new Point(row, col - 1));
            }
            // Right 
            if(col + 1 < colN && board[row][col + 1] == 'O'){
                board[row][col + 1] = '+';
                queue.offerLast(new Point(row, col + 1));
            } 
        }
        
        // Traversal All nodes and change '+' to 'O', 'O' to 'X'
        for(int i = 0; i < rowN ; i++){
            for(int j = 0; j < colN; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                } else if(board[i][j] == '+'){
                    board[i][j] = 'O';
                } else{
                    continue;
                }
            }
        }
    }
}

class Point{
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
```

## Solution - DFS

題目要我們找出四個邊中出現的'O'，並將與其connected（四方位相接）的所有'O'保留'O'，以及將剩餘沒有被接觸的'O'改為'X'。

解題思路：將四周拓展出去的'O'改成'*'，最後在遍歷一次整個圖，將'O'全部改成'X'，時間複雜度 O(mn)。

1. 遍歷四個邊（右邊與底邊需要檢查 row > 1 && col > 1)，如果在此時遇到 'O' 將其丟入 DFS Function 處理
2. DFS Function: 如果被丟入的'O'，進行處理換成'*'，接著，將四方位檢查是否valid後，丟入DFS繼續處理。
3. 遍歷整個圖，將'O'換成'X'，'*'換成'X'

```
四方位檢查：
上 row - 1 / col ; row - 1 > 0 -> row > 1
下 row + 1 / col ; row + 1 < board.length 
左 row / col - 1 ; col - 1 > 0 -> col > 1
右 row / col + 1 ; col + 1 < board[0].length
```

```java
class Solution {
    public void solve(char[][] board) {
        if(board.length == 0) return;
        int row = board.length;
        if(board[0].length == 0) return;
        int col = board[0].length;
        
        // Traverse First and Last Row 
        // board[0][i]  & board[row - 1][i] (row > 1)
        for(int i = 0; i < col; i++) {
            if(board[0][i] == 'O') {
                Solution.dfsHelper(0, i, row, col, board);
            }
            
            if(row > 1 && board[row - 1][i] == 'O') {
                Solution.dfsHelper(row - 1, i, row, col, board);
            }
        }
        
        // Traverse First and Last Col
        // board[i][0]  & board[i][col - 1] (col > 1)
        for(int j = 0; j < row; j++) {
            if(board[j][0] == 'O') {
                Solution.dfsHelper(j, 0, row, col, board);
            }
            
            if(col > 1 && board[j][col - 1] == 'O') {
                Solution.dfsHelper(j, col - 1, row, col, board);
            }
        }
        
        // Change all 'O' into 'X && all '*' into 'O'
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(board[i][j] == 'O') board[i][j] = 'X';
                if(board[i][j] == '*') board[i][j] = 'O';
            }
        }
    }
    
    public static void dfsHelper(int r, int c, int row, int col, char[][] board) {
        if(board[r][c] == 'O') {
            board[r][c] = '*';
            
            // Upper
            if(r - 1 > 0 && board[r - 1][c] == 'O') {
                Solution.dfsHelper(r - 1, c, row, col, board);
            }
                
            // Down
            if(r + 1 < row && board[r + 1][c] == 'O') {
                Solution.dfsHelper(r + 1, c, row, col, board);
            }
            
            // Left
            if(c - 1 > 0 && board[r][c - 1] == 'O') {
                Solution.dfsHelper(r, c - 1, row, col, board);
            }    
            
            // Right
            if(c + 1 < col && board[r][c + 1] == 'O') {
                Solution.dfsHelper(r, c + 1, row, col, board);
            }
        }
    }
}

```