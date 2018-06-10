# 762. Prime Number of Set Bits in Binary Representation
Given two integers L and R, find the count of numbers in the range [L, R] (inclusive) having a prime number of set bits in their binary representation.

(Recall that the number of set bits an integer has is the number of 1s present when written in binary. For example, 21 written in binary is 10101 which has 3 set bits. Also, 1 is not a prime.)

```
Example 1:

Input: L = 6, R = 10
Output: 4
Explanation:
6 -> 110 (2 set bits, 2 is prime)
7 -> 111 (3 set bits, 3 is prime)
9 -> 1001 (2 set bits , 2 is prime)
10->1010 (2 set bits , 2 is prime)
Example 2:

Input: L = 10, R = 15
Output: 5
Explanation:
10 -> 1010 (2 set bits, 2 is prime)
11 -> 1011 (3 set bits, 3 is prime)
12 -> 1100 (2 set bits, 2 is prime)
13 -> 1101 (3 set bits, 3 is prime)
14 -> 1110 (3 set bits, 3 is prime)
15 -> 1111 (4 set bits, 4 is not prime)
```


## Solution
- 使用 Java build-in function: bitCount(int) 來計算某個integer內binary呈現後1的數量
- 此題的Input有限制，不一定要直接使用 helper funciton 來判斷是否為 prime number
- 可以將所有可能的 prime number 放入一個 set 當中來做判斷
- L, R will be integers L <= R in the range [1, 10^6].
- R - L will be at most 10000.
- 10 ^ 6  約等於 2 ^ 20 = 1048576，所以最多就是20個位數，Prime 從 2開始到 19。
- [2,3,5,7,11,13,17,19]

```java
class Solution {
  public int countPrimeSetBits(int L, int R) {
      int count = 0;
      for(int i = L; i <= R; i++) {
          if(isPrime(Integer.bitCount(i))) {
              count++;
          }
      }
      return count;
  }
  
  public static boolean isPrime(int num) {
      if(num == 1) return false;
      for(int i = 2; i < num; i++) {
          if(num % i == 0) {
              return false;
          }
      }
      return true;
  }
}
```


```java
class Solution {
    public int countPrimeSetBits(int L, int R) {
        int count = 0;
        Set<Integer> primeSet = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19));
        for(int i = L; i <= R; i++) {
            if(primeSet.contains(Integer.bitCount(i))) {
                count++;
            }
        }
        return count;
    }
}
```