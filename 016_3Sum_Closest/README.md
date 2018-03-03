# 3 Sum

## Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

```
    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
```

## 思路
- For Loop 走過一所有的數，先挑掉一個，看剩下的數裡面與挑選的值的合，哪個最接近Target
- 先選了 nums[0] + nums[1] + nums[2] 當作是 result
- 每次比較 nums[i] + nums[left] + nums[right] = sum 的數值和result，哪個比較接近(target剪掉之後的絕對值較小)
- 更新res，如果res == target，直接return res
- 如果 target > sum -> 數字太小，left++，如果 target < sum -> 數字太大，right--

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