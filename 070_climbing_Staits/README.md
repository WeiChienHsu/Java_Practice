# Climbing Stair

## Solution
1: 1

2 : 1 + 1
    2

3 : 1 + 1 + 1
    1 + 2
    2 + 1
    
- n - 1 : 2 
- n - 2 : 1

4 : 1 + 1 + 1 + 1
    1 + 1 + 2
    1 + 2 + 1
    2 + 1 + 1
    2 + 2

- n - 1 : 3
- n - 2 : 2

- Used a [n-1] and [n-2] solution to get the result of n

## Opt - Fib
T(n) = T(n-1) + T(n-2)
- SubProblems : T(n-1) = recursoin(n-1) && T(n-2) = recursion(n-2)
- 前面計算過的數，用數組記下來，達到用前面計算過的數字來算後面的樹
- Space : O(n)
- Time : O(n)
```
1  2  3  4  5  6  7
_____________________
1  2  3  5  8  13  21
_____________________
```
- Time: O(n)
- Space: O(1)
- 用兩個指針去解。


```
                    O
                   /  \
1   1             O    \
                 / \    \
2   2           O   |    O
               / |  |    /\       
3   3         O  |  O   O  \ 
            / |  |  | \ |\  \
4   5      O  |  O  O | O |  O
          /   |  |  | | | |  | 
 5   8    O   O  O  O O O O  O
```