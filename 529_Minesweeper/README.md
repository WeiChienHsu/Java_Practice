# Minesweeper

You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:

If a mine ('M') is revealed, then the game is over - change it to 'X'.
If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.

## Solution
- 踩地雷遊戲，一共有 M: 沒被點開的炸彈 / E 沒被點開的空地（周圍可能有0-8個地雷) / X: 被點開的炸彈 / 1 - 8: 周圍有的炸彈數
- 如果一始就點到M，直接標記成X，結束搜索
- 如果一開始點到沒被點開的空地，要先放入Queue中，代表著要去搜尋四周的地
- 搜尋四周的地可能有兩種結果，一種是count == 0，沒有任何炸彈，要把這些地都標記為B（記得標自己），並把周遭的地都標記為B之後，放入Queue當中，繼續搜索
- 如果周遭有炸彈(count > 0)，將該點標記成炸彈的個數後，不需要再操作，return 
- 經過以上操作，會將所有點都翻開，圖上將只有兩種狀況，數字+B+M，或是點到了炸彈 X+M+E+數字

1. Check the Click
2. If Click is 'M', mark it as 'X' and return the board.
3. If Click is 'E', we need to count it's surround, how many mines are there surrounded. (Need a Queue to save those B needed to be searched)
4. Used a helper int[] {{1,0},{-1,0},{0,1},{0,-1}, {1,1},{1,-1},{-1,1},{-1,-1}} to count the mines (met 'M', or 'X')
5. If there are more than one mines, mark it with number of surrounding mine(s), and return the board.
6. If there is no mine around put all it's around into a Queue (to process) and mark them as 'B' (to avoid searching them again)

```java
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        
        
        int row = board.length;
        int col = board[0].length;
        
        Deque<int[]> queue = new ArrayDeque<>();
        int[][] helper = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, -1}, {-1, 1}, {1, 1}, {-1, -1}};
        
        char[][] res = new char[row][col];
    

        queue.offerLast(click);
        
        while(!queue.isEmpty()) {
            int[] cur = queue.pollFirst();
            int x = cur[0];
            int y = cur[1];
            
            // If click is 'M', mark it as 'X' and return 
            if(board[x][y] == 'M'){
                board[x][y] = 'X';
            } else {
                
                // Count the mines sround
                int count = 0;
                for(int[] dir : helper) {
                    int newRow = x + dir[0];
                    int newCol = y + dir[1];
                    if(newRow < 0 || newRow >= row || newCol < 0 || newCol >= col) {
                        continue;
                    }
                    if(board[newRow][newCol] == 'M' || board[newRow][newCol] == 'X') {
                        count++;
                   }
                }

                
                // Two condition: 
                // count == 0 : BFS the rest of nodes by puting into the Queue and mark them as 'B'
                // count > 0 : Add the number into cur position and return 
                
                if(count > 0) {
                    board[x][y] = (char)(count + '0');
                } else {
                    board[x][y] = 'B';
                    for(int[] dir : helper) {
                        int newRow = x + dir[0];
                        int newCol = y + dir[1];
                        if(newRow < 0 || newRow >= row || newCol < 0 || newCol >= col){
                            continue;
                        }
                        if(board[newRow][newCol] == 'E') {
                            queue.offerLast(new int[]{newRow, newCol});
                            board[newRow][newCol] = 'B';                               
                        }

                    }
                }
            }
            
        }
        return board;
    }
}
```