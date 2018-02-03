# Max Product Subarray

## Solution 
- Record each max and min values
- Compare (max * num) & (min * min) & num
- Remind: If we change the max value, then the judgment of min value need to use temp value 

- Move Head : Expand current result
- Move Tail : Reset the Subarray

#### Dynamic Programming - Greedy
- max[i] = Math.max(Math.max(max[i-1] * nums[i], min[i-1] * nums[i]), nums[i]);
- min[i] = Math.min(Math.min(max[i-1] * nums[i], min[i-1] * nums[i]), nums[i]);


```java
    public static int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int result = Integer.MIN_VALUE;
        for(int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]) , nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
            result = Math.max(result, max);
        }
        return result;
    }
```