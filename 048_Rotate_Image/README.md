# 48. Rotate Image

```
Example 1:

Given input matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
Example 2:

Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
```


## Solution

順時針與逆時針旋轉圖。

順時針：將上下row依次swap，並且針對[對角]的Node進行swap，關鍵在使用 i = 0, j = i + 1 的 double for loop

```
  [1,2,3],
  [4,5,6],
  [7,8,9]

  [7,8,9],
  [4,5,6],
  [1,2,3]

  [7,4,9],
  [8,5,6],
  [1,2,3]

  [7,4,1],
  [8,5,6],
  [9,2,3]

  [7,4,1],
  [8,5,2],
  [9,6,3]
```

```java
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // Rotating the top and last row by steps
        for(int first = 0, last = n - 1; first < last; first++, last--) {
            int[] temp = matrix[first];
            matrix[first] = matrix[last];
            matrix[last] = temp;
        }
        
        // Rotating the [0][1] [0][2] [1][1] ...
        for(int r = 0; r < n; r++) {
            for(int c = r + 1; c < n; c++) {
                int temp = matrix[r][c];
                matrix[r][c] = matrix[c][r];
                matrix[c][r] = temp;
            }
        }
    }
}
```