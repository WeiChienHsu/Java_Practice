# Missing Number

Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

```
Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
```

## Sum Method
先求總和，依序減去array中的value，剩餘的數字為缺失值。

```java
    public int missingNumber(int[] nums) {
        int sum = (1 + nums.length) * nums.length / 2;
        for(int i = 0; i < nums.length; i++) {
            sum -= nums[i];
        }
        return sum;
    }

```

## XOR Method

使用 XOR 運算，將x初始設為0，在沒有缺失數字的情況下， 每個數字出現兩次，但現在有了缺失，

經過位運算後，x的值將會是缺失的數值（因為沒有兩次XOR)

```
x = 0
for i = 1 to n
  x = x ^ i ^ nums[i - 1]
return x
```

```
a ^ a = 0
0 ^ 0 = 0
a ^ 0 = a
a ^ b ^ a = b
```

```java
class Solution {
    public int missingNumber(int[] nums) {
        int x = 0;
        for(int i = 1; i <= nums.length; i++) {
            x = x ^ i ^ nums[i - 1];
            // i -> 1, 2, 3
            // nums[i-1] -> 1 0 3
            // x -> 0 -> 3 -> 2-> 2
        }
        return x;
    }
}
```