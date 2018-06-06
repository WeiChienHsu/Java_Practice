## Find MIN ROTATED Sorted Array

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

The array may contain duplicates.

## Solution
跟上一題一樣，但需要考慮去重複的狀況！

必須先判斷end和mid有無相同，因為如果start == end == mid 的情況發生時，最小的一定在左側。

if(nums[mid] == nums[end]) -> end --;

if(nums[mid] == nums[start]) -> start++;

剩餘跟前面一樣：
1. nums[start] < nums[mid] < nums[end] : 保留左邊
2. nums[start] > nums[mid] > nums[end] : 保留右邊
3. mid 最大 : 留下 start 和 end 小的一邊
4. mid 最小 : 留下 start 和 end 大的一邊


```java
class Solution {
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            // Dedupe
            if(nums[mid] == nums[end]) {
                end--;
            } else if(nums[mid] == nums[start]){
                start++;
            // Left Side is Larger
            } else if(nums[mid] > nums[start] && nums[mid] < nums[end]) {
                end = mid;
            // Right Side is Larger
            } else if(nums[mid] > nums[end] && nums[mid] < nums[start]) {
                start = mid;
            // Mid is the largest
            } else if(nums[mid] > nums[end] && nums[mid] > nums[start]) {
                if(nums[start] >= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            // Mid is the Smallest
            } else if(nums[mid] < nums[end] && nums[mid] < nums[start] ){
                if(nums[start] >= nums[end]) {
                    end = mid;
                } else {
                    start = mid;
                }
            }
        }
        return nums[start] > nums[end]? nums[end] : nums[start];
    }
}
```