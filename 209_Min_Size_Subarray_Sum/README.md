# Min Size Subarray Sum
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
```
Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint
```

## Two Pointers
- Right Pointer: 只要 Sum < target ， 就不斷向前
- Left Pointer: 只要 Sum >= target， 紀錄當前 Length (與minLength比較)， Left 向前

```java
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums.length == 0) return 0;
        
        int left = 0, right = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        
        if(sum >= s) return 1;
        
        for(right = 0; right < nums.length; right ++) {
            sum += nums[right];
            while(sum >= s) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left++];
            }  
        }
        
        return minLength == Integer.MAX_VALUE? 0 : minLength;
    }
}

```