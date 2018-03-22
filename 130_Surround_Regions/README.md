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