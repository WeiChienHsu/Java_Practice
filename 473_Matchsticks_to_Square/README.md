 # Matchsticks_to_Square
 


### Partition Problems
the partition problem (or number partitioning) is the task of deciding whether a given multiset S of positive integers can be partitioned into two subsets S1 and S2 such that the sum of the numbers in S1 equals the sum of the numbers in S2. 
- The partition problem is NP-complete.

### Without using DP- (instead DFS)
When I trying to think how to apply dynamic programming solution of above problem to this one (difference is divid S into 4 subsets), I took another look at the constraints of the problem:

- The length sum of the given matchsticks is in the range of 0 to 10^9.
- The length of the given matchstick array will not exceed 15.
- Sounds like the input will not be very large… Then why not just do DFS? In fact, DFS solution passed judges.

## Solution
- 正方形的四边长都是一样的。
- 如果数组里的所有元素之和不能被4整除，则不能被拼成一个正方形。
- 如果可以被四整除，我们可以一步步往下搜索，直到找到一个可行的拼法。

```java
public class Solution {
    public boolean makesquare(int[] nums) {
    	if (nums == null || nums.length < 4) return false;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 4 != 0) return false;
        
        Arrays.sort(nums);
        reverse(nums);
        
    	return dfs(nums, new int[4], 0, sum / 4);
    }
    
    private boolean dfs(int[] nums, int[] sums, int index, int target) {
    	if (index == nums.length) {
    	    if (sums[0] == target && sums[1] == target && sums[2] == target) {
    		return true;
    	    }
    	    return false;
    	}
    	
    	for (int i = 0; i < 4; i++) {
    	    if (sums[i] + nums[index] > target) continue;
    	    sums[i] += nums[index];
            if (dfs(nums, sums, index + 1, target)) return true;
    	    sums[i] -= nums[index];
    	}
    	
    	return false;
    }
    
    private void reverse(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++; j--;
        }
    }
}
```