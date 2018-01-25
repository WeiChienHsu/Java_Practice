# Best time to sell and but a stock
## Similar to the Maximun subarray problem (Kadane's Algorithm)
```
Input: [7, 1, 5, 3, 6, 4]
Output: 5

max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
```


## Solution
- Record the max value current by add new value to maxCurr 
- if < 0, reset to 0;
- if > 0, add value
```
[1, 3, -2, 1]

curMax -> 1 -> 4 -> 2 -> 1

[1, 2 , -4, 4]

curMax -> 1 -> 3 -> -1(change to 0) -> 4

```
- Record the max value so far
```java
class Solution {
    public int maxProfit(int[] prices) {
        int maxCur = 0;
        int maxSoFar = 0;
        for (int i = 0; i < prices.length - 1; i++){
            maxCur = Math.max(0, maxCur += prices[i+1] - prices[i]);
            maxSoFar = Math.max(maxSoFar, maxCur);
        }
        return maxSoFar;
        }
    }

```