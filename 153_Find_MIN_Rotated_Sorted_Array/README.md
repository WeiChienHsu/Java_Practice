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

## 判斷Mid, Start, End之間的四種可能性
1. Mid 最大 -> 比較 Start 和 End : 保留較小的那邊
```
3 4 5 1 2 
```

2. Mid 最小 -> 比較 Start 和 End : 保留較大的那邊（因為數列遞增，比mid小的數會在左邊)
```
5 1 2 3 4
```

3. Mid > start && Mid < End : 保留左邊 -> end = mid
```
1 2 3 4 5
```

4. Mid < start && Mid > End : 保留右邊 -> start = mid
```
4 5 1 2 3
```

```java
class Solution {
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            // When mid is the largest
            if(nums[mid] > nums[start] && nums[mid] > nums[end]) {
                if(nums[start] > nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            // When mid is the smallest    
            } else if(nums[mid] < nums[start] && nums[mid] < nums[end]){
                if(nums[start] > nums[end]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else if(nums[mid] > nums[start] && nums[mid] < nums[end]) {
                end = mid;
            } else if(nums[mid] > nums[end] && nums[mid] < nums[start]) {
                start = mid;
            } 
        }
        return nums[start] > nums[end]? nums[end] : nums[start];
    }
}
```