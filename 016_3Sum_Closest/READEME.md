# 3 Sum

## Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

```
    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
```

## Solution
- Sort the array!!!!!!!
- Pick nums[0] + nums[1] + nums[2] to be the res temporatory
- Then use left and right pointer to check the value
* for loop from the first number to the last n number
```
 O  O  O  O  O  O  O  O  O
 *  *  *

  O  O  O  O  O  O  O  O  O
  *  *                    *
```
```java
while(left < right) {
    int sum = nums[i] + nums[left] + nums[right];
    if(Math.abs(target - sum) <= Math.abs(target - res)) {
        res = sum;
        if (res == target) return res;
    }
```

- sum = nums[i] + nums[j] + nums[k]
* If target - sum =< res -> res = sum
* check if target = sum
* If target - sum > res -> keep res
- If sum > target right--
- If sum < target left++ 
```java
if(Math.abs(target - sum) <= Math.abs(target - res)) {
        res = sum;
        if(res == target) return res;
    }
    if(sum > target) right--;
    else if(sum < target) left++;
}
```