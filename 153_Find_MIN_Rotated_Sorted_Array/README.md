# Find Minimum in Rotated Sorted Array

```
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
```

## Solution - Find the first pisition Small or equal to the last number
```
    / |
   /  |
  /   |
A/    |
--------------
      |    /
      |   / B
      |  /
      | /
```
- Fint <A or < = B
- Since if the array didn't be really rotated likes:
```
0 1 2 3 4 5 6 7
```
- Then you still should find the first position of number <= 7
- But not the number < 0

```java
class Solution {
  public int findMin(int[] nums) {
      if(nums.length == 1) {
         return nums[0]; 
      }
      
      int start = 0;
      int end = nums.length - 1;
      while(start + 1 < end) {
          int mid = start + (end - start) / 2;
          if(nums[mid] == nums[end]) {
              end = mid;
          } else if (nums[mid] < nums[end]) {
              end = mid;
          } else {
              start = mid;
          }
      }
      
      return (nums[end] > nums[start])? nums[start] : nums[end];
  }
}
```