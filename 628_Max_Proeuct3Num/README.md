# Max 3 Porduct numbers

## Solution :
- Sort the array
- get first 3 large positive numbers or 2 smallest nagitive number and 1 largest positive number
```java
    public static int maxThree(int[] nums){
         if(nums.length == 0) return 0;
         if(nums.length == 1) return nums[0];
         if(nums.length == 2) return nums[0] * nums[1];
         int max = Integer.MIN_VALUE;
         Arrays.sort(nums);
         int n = nums.length -1;
         max = Math.max((nums[0]*nums[1]*nums[n]),nums[n]*nums[n-1]*nums[n-2]);
         return max;
    }
```