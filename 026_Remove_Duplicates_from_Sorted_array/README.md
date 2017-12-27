# Remove Duplicates from Sorted Array

## In-place

```
(++i) means to add 1 to i itself and return the new value
(id++) means to add 1 to id itself and return the old value
```

# Solution
```java
class Solution {

    public int removeDuplicates(int[] nums){
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[index] == nums[i]) {
                continue;
            } else {
                nums[++index] = nums[i];
            }
        }
        return index + 1;
    }
}

```