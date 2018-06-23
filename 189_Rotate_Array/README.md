# Rotate Array


## Solution - Slow

一次一次操作，每次執行一次 rotate ， 把最後一個取出，然後從後往前複製每個數字。

```java
  public static void rotate(int[] nums, int k) {
      // Shorten the K
      k %= nums.length;
      int count = 0;

      while(count < k) {
          int temp = nums[nums.length - 1];
          for(int i = nums.length - 1; i > 0; i--) {
              nums[i] = nums[i - 1];
          }
          nums[0] = temp;
          count++;
      }

```

## Solution - Fast

先反轉一次array，抓 [0, k -1] & [k, len - 1] 再次反轉，就會得到解答。

```java
class Solution {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}

```