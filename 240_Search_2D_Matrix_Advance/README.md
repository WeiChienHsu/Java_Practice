# 2D Matrix Advance
## Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

```
Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
```
## Solution - 1 : Separater to the subproblems

- Still Used a binary Search
```java
int midX = startX + (endX - startX) / 2 ;
int midY = startY + (endY - startY) / 2 ;
```

- Used Recursive methods
```java
return binarySearch(matrix,target,0,0,m-1,n-1);
```

```
Sx Sy                ___________
  O      O      O    |  O     O |   
              mX mY  |Sx+mY+1   |
  O      O      O  -->  O     O |
___________________  |          |
|mX+1 Sy           | |          |
|  O      O      O | |  O     O | 
|__________ mX+1 mY  |_____     |
                             endX endY


4 Area:
Larger:
SX MY+1 & EX EY
MX+1 Sy & EX MY

Smaller:
SX SY & EX MY -1
MX -1 & EY
```
```java
if(matrix[midX][midY] == target){
    return true;
} else if(matrix[midX][midY] > target) {
    // Case 2: go left and upper sub matrix
    return binarySearch(matrix,target,startX,startY,endX,midY-1) ||
            binarySearch(matrix, target, startX, midY, midX -1, endY);
} else {
    // Case 3: go down and right sub matrix
    return binarySearch(matrix,target, startX,midY+1,endX,endY) ||
            binarySearch(matrix,target,midX+1,startY,endX,midY);
}
```

- Take attention on Corner Case assumption
```java

```

## Solution2 - Start from the leftest and upest point
```
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
  ```
  - Start from 15
  - target < current : go left
  - target > current : go down

- Conrner Case:
```java
int row = matrix.length;
if(row == 0 || matrix[0] == null) return false;
int col = matrix[0].length;
if(col == 0) return false;
```
- Run the Rule
```java
 // Start from the top-right point
int curRow = 0;
int curCol = col - 1;

while(curRow < row && curCol >= 0) {
    if(matrix[curRow][curCol] > target) {
        curCol--;
    } else if(matrix[curRow][curCol] < target) {
        curRow++;
    } else {
        return true;
    }
}

return false;
}
```