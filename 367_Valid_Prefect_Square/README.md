# Valid Prefect Square

Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

```
Example 1:

Input: 16
Returns: True
Example 2:

Input: 14
Returns: False
```

## Solution - Binary Search
- 記得 mid 不能用 int ， 因為Integer有最大數字的限制，要使用long的資料型態。
- 但賦予 start 或 end 的時候，記得(int)改回 Integer

```java
class Solution {
    public boolean isPerfectSquare(int num) {
        int start = 1;
        int end = num;
        while(start + 1 < end) {
            long mid = start + (end - start) / 2;
            
            if(mid * mid == num) return true;
            
            if(mid * mid > num) {
                end = (int) mid;
            } else {
                start = (int) mid;
            }
        }

        return start * start == num || end * end == num;
    }
}
```