# Squr(x)

## Compute and return the square root of x.
```
Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since we want to return an integer, the decimal part will be truncated.
```

## solution
- Binary Search:
* left = 1
* right = Integer.MAX_VALUE
- Give a mid
- If Mid^2 > x : right = mid - 1 (give up larger)
- if Mid^2 < x :
- Check if (mid + 1) ^ 2 > x which mean the answer is mid
- if not return left = mid + 1
```java
 if (x == 0) return 0;
        
        int left = 1, right = Integer.MAX_VALUE;
        while (true) {
            int mid = left + (right - left)/2;
            if (mid > x/mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x/(mid + 1))
                    return mid;
                left = mid + 1;
            }
        }
```