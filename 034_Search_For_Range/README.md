# Find the Range

Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

```
Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
```

## Solution - Bainary Search
利用Binary Search 找 "第一個等於或是大於該數的Index"
先得到Start Point，也就是確定Array內有沒有Target，有兩種情況：
1. nums[start] != target : 沒有 Target ， 直接 Return new int[] {-1, -1}
2. start == nums.length : 沒有 Target ， 直接 Return new int[] {-1, -1}

接著，找Target + 1這個數字的Index，會有兩種情況：
1. nums[end] == target : 直接使用 end 當作 end index
2. nums[end] != target : 代表找到了Target+1的index，使用 end - 1 當作 end index

```java
class Solution {
  public int[] searchRange(int[] nums, int target) {
      if(nums.length == 0) return new int[]{-1, -1};
      
      int start = Solution.findIndexOfFirstGreaterOrEqual(nums, target);
      
      if(nums[start] != target || start == nums.length) return new int[]{-1, -1};
      int end = Solution.findIndexOfFirstGreaterOrEqual(nums, target + 1);
      
      // 如果找到的是 > target 的數字，要--，如果找到剛好一樣大的，要直接return
      return new int[]{start, nums[end] == target? end : end - 1};
  }
  
  // 找尋第一個大於等於Target的Index
  public static int findIndexOfFirstGreaterOrEqual(int[] nums, int target) {
      int start = 0, end = nums.length - 1;
      while(start + 1 < end) {
          int mid = start + (end - start) / 2;
          if(nums[mid] >= target) {
              end = mid;
          } else {
              start = mid;
          }
      }   
      return nums[start] == target? start : end;
  }
}
```