# Binary Number with Alternating Bits

## Solution

我們要測試該數字是否為 10101010 這樣交替的二進位數字

```
n            =  10101010
n >> 1       =  01010101
n & n>>1     =  00000000
n ^ n>>1     =  11111111
n ^ n>>1 + 1 = 100000000

(n ^ n >> 1 + 1) & (n ^ n >> 1) == 0

簡化

n = n ^ (n>>1);
比較 n 和 n + 1 的 and operator logic

```

```java
class Solution {
    public boolean hasAlternatingBits(int n) {
        n = n ^ (n>>1);
        return (n & n+1) == 0;
    }
}
```