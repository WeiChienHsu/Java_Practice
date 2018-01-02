# Spiral Matrix Advance
## Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

```
For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
```

## Solution - Used the boundries

- Changign the boundary and using for loop to go through each row and col
```
1  2  3  4  5
12 13 14 15 6
11 10 9  8  7
```

- Boundry
```
1 -> 5 : matrix[rowMin][i] i : colMin -> colMax ; rowMin++
6 -> 7 : matrix[i][colMax] i : rowMin -> rowMax ; colMax--
8 -> 11 : matrix[rowMax][i] i : colMax -> colMin ; rowMax--
12 : matrix[i][colMin] i : rowMax -> rowMin ; colMin++
-----
13 -> 15 : matrix[rowMin][i] i : colMin -> colMax ; rowMin++
```
```java
    while(colMin <= colMax && rowMin <= rowMax) {
    for(int i = colMin; i <= colMax; i++){
        matrix[rowMin][i] = count++;
    }
    rowMin++;

    for(int i = rowMin; i <= rowMax; i++) {
        matrix[i][colMax] = count++;
    }
    colMax--;

    if(rowMin > rowMax || colMin > colMax) break;

    for(int i = colMax; i >= colMin; i--) {
        matrix[rowMax][i] = count++;
    }
    rowMax--;

    for(int i = rowMax; i >= rowMin; i--) {
        matrix[i][colMin] = count++;
    }
    colMin++;
```

- Need to check if minCol > maxCol and minRow > maxRow middle the loop (In the problem, we don't need to deal with this special case since we go a n*n matrix, but if the matrix was n*m the Max row and Min col will meet this issue)
- Add a variable count to count the number which needs to be added