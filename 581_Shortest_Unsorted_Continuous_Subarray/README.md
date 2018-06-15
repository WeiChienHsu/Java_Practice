## 581. Shortest Unsorted Continuous Subarray

```
Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
```

## Solution

找出最早出現沒有“排序”的數列位置，以及最後一個被排序的數列位置相減。

- 利用 array.clone() 克隆題目給的array
- 再利用 Arrays.sort(array) 排序好目標array
- 用start pointer 遍歷一遍array，看最早產生非排序的位置，如果start == end 代表已經排序好，return 0
- 再用 end pointer 找到第一個沒有排序好的數字
- 將兩個index相減 + 1


```java
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        // [2, 3, 4, 5, 6]
        // [2, 5, 3, 4, 6]
        
        int start = 0;
        int end = nums.length - 1;
        
        while(start < end && nums[start] == sortedNums[start]) start++;
        if (start == end) return 0;
        
        while(end > 0 && nums[end] == sortedNums[end]) end--;
        
        return end - start + 1;
    }
}
```