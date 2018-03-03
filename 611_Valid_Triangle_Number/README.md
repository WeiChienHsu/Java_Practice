# Valid Triangle Number
Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

```
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
```
## Two Condition to make a triangle
- a <= b<= c
- a + b > c

### Solution
- Chose from the back of array to the index 2
- to see if the rest of number could greater than target!
- if nums[left] + nums[right] > target, means all the nums[right] with the rest of array could have sum greater than target, so we just need to count (right - left) times and move left the right pointer

```java
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for(int i = nums.length - 1;  i >= 2; i--) {
            int third = nums[i];
            int left = 0;
            int right = i - 1;
            while(left < right) {
                if(nums[left] + nums[right] > third) {
                    count += right - left;
                    right--;
                } else {
                    left++;
                }   
            }
        }
        return count;
    }
}
```