# Find the Dupliace Number
[1, 3, 4, 2, 2] -> Return 2

## HashSet - Used Additional Space

- Set<Integer> set = new HashSet<>();
- set.contains(Object o)
- set.add(Object o)

```java
class Solution {
    public int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            if(!set.contains(nums[i])) {
                set.add(nums[i]);
            } else {
                return nums[i];
            }
        }
        return nums[0];
    }
}
```
