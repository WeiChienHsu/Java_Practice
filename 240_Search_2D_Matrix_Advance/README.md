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