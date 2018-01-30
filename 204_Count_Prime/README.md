# Count Prime

## Solution - Primative 
```

2  3  4  5  6  7  8  9  10  11 
   2  2  2  2  2  2  2   2   2    
         3     3     3       3 
         4     4             4 
               5             5   
               6             6
                             7
                             8
                             9
                             10
-> 2 3 5 7 11
```

- 重複除以2 除以3 除以4 O(N^2)
- DP: 避免重複出現 
- 先全部除以2，標記 notPrime
- 2 -> 4 -> 6 -> 8
- 從下個數字3開始，略過notPrime
- 3 -> 9 
- Time O(n)
- Space O(n)
- End Point 優化 cur * i >= n  ----> i <= bound (Math.sqrt(n))

```
2  3  4  5  6  7  8  9  10  11 
2     *     *     *      *
  3                  *
         5     
               7            
                            11
-> 2 3 5 7 11
```