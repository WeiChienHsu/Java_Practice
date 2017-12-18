# Remove Elements
## Given an array and a value, remove all instances of that value in-place and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

```
Given nums = [3,2,2,3], val = 3,

Your function should return length = 2, with the first two elements of nums being 2.
```

## Solution

- Give a len variable, nums[i] = value, remove it by replacing --len
```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        for(int i = 0; i < len; i++) {
            while(nums[i] == val && i < len){
                nums[i] = nums[--len];
            }
        }
        return len;
    }
}
```