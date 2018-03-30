# Target Sum
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

```
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
```

## Solution
- Draw a Strategy Tree [1, 1, 1] target = 1
```
nums.length = 3

pos = 0                 0
                    /      \
pos = 1            1       -1
                 /   \    /   \
pos = 2        1    -1   1     -1
              / \   / \  / \   / \
pos = 3      1  -1  1 -1 1  -1 1  -1
 curVal =   3   1   1  -1 1  -1 0  -2  -> result = 3
```

- 傳入 Target, 當前position, 當前 value
- 每到下面一層，有兩種方式，一是 value =  value + nums[pos] 一是 value = value - nums[pos]
- 當 position == nums.length ， 代表到達了最底端，檢查當前value是否等於Target

```java
class Solution {
    public int result = 0;
    
    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null) {
            return 0;
        }
        
        dfsHelper(nums, 0, 0, S);
        return result;
    }
    
    public void dfsHelper(int[] nums, int pos, int curValue, int target) {
        if(pos == nums.length) {
            if(curValue == target){
                result++;
            }
            return;
        }
        dfsHelper(nums, pos + 1, curValue + nums[pos], target);
        dfsHelper(nums, pos + 1, curValue - nums[pos], target);
    }
}
```

## Optimization 優化

If the sum of all elements left is smaller than absolute value of target, there will be no answer following the current path. Thus we can return.
- 先得到一個sums Array，代表著剩餘的nums可以組成的最大正整數
```
nums = [1,1,1]
sums = [3,2,1]
```

- 修改傳入的值為remainTarget，每次進入下層之前，將該層Target - nums[pos]紀錄
- 如果 sums[pos] < remainTarget，代表著之後的值就算全為正整數，也不可能得到答案，故可以直接return，省略掉後續的操作！
- dfsHelper(int[] sums, int nums, int remainTarget, int pos)
- 兩種狀況 return 
- 1) if(remainTarget > sums[pos]) return
- 2) 到底的狀況，return 避免pos過界:
```java
if(nums.length == pos) {
  if(remainTarget == 0) result++ ; 
  return ;
}
```

```java
class Solution {
    public int result = 0;
    
    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null) return 0;
        
        int n = nums.length;
        int[] sums = new int[n];
        
        sums[n - 1] = nums[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            sums[i] = sums[i + 1] + nums[i];
        }
        
        dfsHelper(nums, sums, S, 0);
        return result;
    
    }
    
    public void dfsHelper(int[] nums, int[] sums, int remainTarget, int pos) {
        if(nums.length == pos) {
            if(remainTarget == 0) {
                result++;
            }
            return;
        }
        
        // The Largest possible value is smaller than remainTarget
        if(Math.abs(remainTarget) > sums[pos]) return;
        
        dfsHelper(nums, sums, remainTarget + nums[pos], pos + 1);
        dfsHelper(nums, sums, remainTarget - nums[pos], pos + 1);
        
    }
}
```
