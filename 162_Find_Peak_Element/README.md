# Find Peak Element
## For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

## Sulotion
- Key: Find First a[i] > a[i+1]
- num[mid] > nums[mid + 1] : end = mid (找左邊有沒有更大的數字)
```
     /\
    /  \ m
   /    \ m + 1
```
- num[mid] < nums[mid + 1] : start = mid (繼續往右找峰值)
```
        /\
 m+1   /  \ 
  m   /    \ 
```
- num[mid] = nums[mid + 1] : start = mid (繼續往右找峰值)
```java
class Solution {
  public int findPeakElement(int[] nums) {
      if (nums.length == 1) {
          return 0;
      }
      
      int start = 0;
      int end = nums.length - 1;
      
      while(start + 1 < end) {
          int mid = start + (end - start) / 2;
          if(nums[mid] == nums[mid + 1]) {
              end = mid;
          } else if (nums[mid] < nums[mid + 1]) {
              start = mid;
          } else {
              end = mid;
          }
      }
      
      return (nums[end] > nums[start]) ? end : start;
  }
}
```