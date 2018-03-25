# Remvoe Duplicate from a sorted array (2 Duplicate)
- Used Two Pointers
- Step Pointer: move each step
- Duplicate Pointer: Stay on the first on the duplicate number

## How Works
- If the lenght between i and j pointers is larger than 1, which means there are third duplicate number, i just skip it, and j still wait on the same index.
- If there is a different number i met from j, j jump to the i index.
- Keep tracking the length between i and j, if it i smaller than 2, count the total length and update the number in array to the current index of i

```java

class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        int j = 0;
        int count = 0;
        
        while(i < nums.length) {
            if(nums[i] != nums[j]) {
                j = i;
            }
            
            if(i - j <= 1 && nums[i] == nums[j]) {
                nums[count] = nums[i];
                count++;
            }
            i++;
        }
        return count;
    }
}
```