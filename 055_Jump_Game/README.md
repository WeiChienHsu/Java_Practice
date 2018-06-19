# Jump Gamp

```
Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
```

## Solution - DP
紀錄目前這個點之前，能夠到達最遠的距離，因為這邊的數字代表的不是「只能跳xx步」，是「最多能跳xx布」，所以只要index小於最遠能跳得點，就可以到達。

```java
class Solution {
    public boolean canJump(int[] nums) {
        int curCanReach = 0;
        for(int i = 0; i < nums.length; i++) {
            if(i > curCanReach) return false;
            curCanReach = Math.max(curCanReach, i + nums[i]);
        }
        return true;
    }
}

```