# Plus One
## int[] -> plus the last digital one

## Solution
- From the last digital
- Check if the number is larger than 9 -> (Need to be carried up)
- Finally, check the first one, if it's 0, if yes, update the array to another
- 999 -> 1000
```
int[] new = int[old.length + 1]
```

```java
    public static int[] plusOne(int[] nums) {
        for(int i = nums.length -1 ; i >= 0; i--) {
            if(nums[i] < 9) {
                nums[i] = nums[i] + 1;
                break;
            } else {
                nums[i] = 0;
            }
        }

        if(nums[0] == 0) {
            int[] result = new int[nums.length + 1];
            result[0] = 1;
            return result;
        }
        return nums;
    }
```