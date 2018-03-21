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



