# Spiral Matrix

## Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

```

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].

```

## Solution: Find the rule of recurison
```
______________________________________
   0     1     2     3     4      5   |
   --------------------------------|  |
|  6     7     8     9     10    11|  |
|                                  |  |
|  12    13    14    15    16    17|  |
|                                  |  |
|  18    19    20    21    22    23|  |
|_____________________________________| 

- They are doing the same thing
```
- In each level, we move left and right, and then scale smaller:

* Scale: Row - 2, Col - 2
* Start Point: (i + offset, j + offset)
```java
helper(res, m, row - 2, col - 2, offset +1)
```
- Give a simple define for each variables
```java
List<Integer> res = new ArrayList<>();
if(matrix == null || matrix.length == 0) return res;
if(matrix[0] == null || matrix[0].length == 0)  return res;

int row = matrix.length;
int col = matrix[0].length;
helper(res, matrix, row, col, 0); // 0 - > start point
return res;
```

### Analyze the Base Case
- One Column left
```java
if(col == 1) {
    for(int i = offset; i < row + offset; i++) {
        res.add(m[i][offset]);
    }
    return;
}
```
- One Row Left
```java
if(row == 1) {
    for(int i = offset; i < col + offset; i++) {
        res.add(m[offset][i]);
    }
    return;
}
```
- Nothing Left
```java
if(row == 0 || col == 0) return;
```

***

## Analyze each side
### Up row
```
Left to Right
X X X X X 
X ----- X
X ----- X
X ----- X

-> Start point : Same as offset
-> Right Limit : offset + col - 1
-> Add: Same Array (offset), different index
```
```java
for(int i = offset; i < offset + col - 1; i++ ) {
    res.add(m[offset][i]);
```

### Right col
```
Up to Down
------- X 
X ----- X
X ----- X
X ----- X

-> Start point : Offset
-> Down Limit : offset + row - 1
-> Add: Different Array (index), right limit offset + col - 1
```
```java
for(int i = offset; i < offset + row - 1; i++ ) {
    res.add(m[i][offset + col - 1]);
```

## Down row
```
Right to Left
------- X 
X ----- X
X ----- X
X X X X X

-> Start point : offset + col - 1
-> Left Limit : offset
-> Add: Same Array (Down Limit), from right limit to left limit 
```

```java
for(int i = offset + col - 1; i > offset; i-- ) {
    res.add(m[offset + row - 1][i]);
```

## left col
```
Down to Up
X -----  
X ----- 
X ----- 
X X X X 

-> Start point : offset + row - 1
-> Up Limit : offset
-> Add: Different Array (Down Limit), Same position(offset)
```

```java
for(int i = offset + row - 1; i > offset; i --) {
    res.add(m[i][offset]);
```

## Give to the next recursion
```java
        helper(res, m, row - 2, col - 2, offset + 1);
```

# BEST SOLUTION !!!!!!!!!!!!
- Changign the boundary and using for loop to go through each row and col
```
1  2  3  4  5
12 13 14 15 6
11 10 9  8  7

1 -> 5 : matrix[rowMin][i] i : colMin -> colMax ; rowMin++
6 -> 7 : matrix[i][colMax] i : rowMin -> rowMax ; colMax--
8 -> 11 : matrix[rowMax][i] i : colMax -> colMin ; rowMax--
12 : matrix[i][colMin] i : rowMax -> rowMin ; colMin++
-----
13 -> 15 : matrix[rowMin][i] i : colMin -> colMax ; rowMin++
```
- Need to check if minCol > maxCol and minRow > maxRow middle the loop