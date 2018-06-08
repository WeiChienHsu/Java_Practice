# Binary Search
Given an sorted integer array - nums/ an integer/ target.
- Find the any/first/last position of target in nums
- Return -1 if target does not exist.

## 時間複雜度計算

二分法：通過O(1)的時間，把規模為n的問題變成 n/2 規模的問題
時間複雜度O(logn)

```
T(n) = T(n/2) + O(1)
= T(n/4) + O(1) + O(1)
= T(n/8) + 3 * O(1)
= T(n/16) + 4 * O(1)
.
.
.
= T(1) + logn * O(1)

T(n) = O(logn)
```

透過O(n)的時間，把規模為n的問題變成 n/2 規模的問題
時間複雜度O(n)

```
T(n) = T(n/2) + O(n)
= T(n/4) + O(n/2) + O(n)
= T(n/8) + O(n/4) + O(n/2) + O(n)
.
.
.
= T(1) + O(n + n/2 + n/4 + ...+ 2 + 1)
= O(2n - 1)
= O(n)
```


- O(1): 一次的加減乘除，一次的if/else判斷
- O(n): For循環
- O: Upper Limit 時間複雜度大概在 logn
- 不論係數，取最高次方
- n: 數據規模（數組長度）
- T(n): 問題規模為n的時間複雜度
- S(n) / M(n): 問空間複雜度

***

## Time Complexity in Coding Interview
從暴力方式去推估，要用什麼優化算法。
- O(1): 極少
- O(logn): 二分法
- O(√n): 分解質因數 (6 = 2 * 3 -> 從 6/2 除到 6/√6)
- O(n): 高頻 (for循環找一個數就解決)
- O(nlogn): 一般可能要排序
- O(n^2): 數組/枚舉/動態規劃
- O(n^3): 數組/枚舉/動態規劃
- O(2^n): 與組合有關的搜索 (Subset子集問題）
- O(n!): 與排列有關的搜索 (n個數有n!個排列方式)

#### 比O(n)更優的時間複雜度，只能是O(logn)的二分法!

***

## Recursion or While Loop?
能用While循環就用While，但要和面試官討論。
(Recursion容易遇上 Stack Overflow)

面試中否使用 Recursion 的幾個判斷條件：
- 面試官是否要求了不使用 Recursion
- 不使用 Recursion 是否會造成實現變得很複雜
- Recursion 的深度是否會很深
- 題目的考點是 Recursion vs Non-Recursion 還是考你是否會Recursion？

***

## 二分法常見痛點
- 死循環
- 循環結束條件倒底是哪個？
```
start <= end
start < end
start + 1 < end
```
- 指針變化到底是哪個？
```
start = mid
start = mid + 1
start = mid - 1
```

### 通用二分法模板

```java
public int binarySratch(int[] nums, int target) {
  if (nums == null || nums.length == 0) {
    return -1;
  }

  int start = 0, end = nums.length - 1;

  while (start + 1 < end) {
    int mid = start + (end - start) / 2;
    if(nums[mid] == target) {
      start = mid;
    } else if (nums[mid] < target) {
      start = mid;
      // or start = mid + 1;
    } else {
      end = mid;
      // or end = mid -1;
    }
  }

  if(nums[start] == target) {
    return start;
  }
  if(nums[end] == target) {
    return end;
  }
    return -1;
  }
}
```
如果用 start < end ，會在兩指針指向同一個地方時結束，start + 1 < end ，只要相鄰就會結束，剩下兩個數。

結束前判斷 start / end ，避免死循環，目的在於減少區間，不一定要在中間就回傳值，只要保證while循環會一直縮減，直到可以直接數到答案。

***

- Last Position : 找最後，要看target後面是否還有
```java
if(nums[mid] == target) {
  start = mid;
}
```

- First Position：找第一，要看target前面是否還有
```java
if(nums[mid] == target) {
  end = mid;
}
```

***

### start + (end - start) / 2

防止 start + end 會越界

***

## 總結

while 循環把數組縮減，使用 start + 1 < end
判斷要找first / last
num[mid] = target時，是要 end = mid / start = mid
結束while循環後，start / end 判斷一次是否為解
都不是的時候輸出啥

***


## Binary Search on Index - OOXX
- 找到滿足某個條件的第一個位置或最後一個位置
- 給一個數組，讓你找數組中第一個/最後一個滿足某條件的位置

OOOOOOOOOOXXXXXXXXX
First Position of Something

```
First Bad Version
1 2 3 4 .. k-1 k k+1
O O O O O X O
```

### Vector (ArrayList) 動態數組
不需要聲明總長有多大（int[] = new int[xxx])

#### 實踐原理： ArrayList （不夠時 x2)
- new了一個ArrayList後，開10個位置。
- 裝滿10個後，裝第十一個，會resize，變成20個位置的數組。
- 過程要Copy前面10個數。

#### 倍增 Double
- ArrayList vector
- 爬蟲訪問網站：訪問不了，一分鐘後，又無法訪問，接著設置兩分鐘後訪問，四分鐘，八分鐘.... Exponential Backoff

#### 題目：很長的 sorted Array內找到Target
- 運用倍增的思想去找到一個 > target的數
- log(k)次，就可以找到 k - 2k 之間的範圍
- 找到之後設置為end，用 binary search找到target
```java
while(count < target) {
  count *= 2;
}
```

### 題目：Find Minimum in Rotated Sorted Array

## 判斷Mid, Start, End之間的四種可能性
1. Mid 最大 -> 比較 Start 和 End : 保留較小的那邊
```
3 4 5 1 2 
```

2. Mid 最小 -> 比較 Start 和 End : 保留較大的那邊（因為數列遞增，比mid小的數會在左邊)
```
5 1 2 3 4
```

3. Mid > start && Mid < End : 保留左邊 -> end = mid
```
1 2 3 4 5
```

4. Mid < start && Mid > End : 保留右邊 -> start = mid
```
4 5 1 2 3
```

```java
class Solution {
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            // When mid is the largest
            if(nums[mid] > nums[start] && nums[mid] > nums[end]) {
                if(nums[start] > nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            // When mid is the smallest    
            } else if(nums[mid] < nums[start] && nums[mid] < nums[end]){
                if(nums[start] > nums[end]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else if(nums[mid] > nums[start] && nums[mid] < nums[end]) {
                end = mid;
            } else if(nums[mid] > nums[end] && nums[mid] < nums[start]) {
                start = mid;
            } 
        }
        return nums[start] > nums[end]? nums[end] : nums[start];
    }
}
```

## Map Differences
HashMap is implemented as a hash table, and there is no ordering on keys or values. 
TreeMap is implemented based on red-black tree structure, and it is ordered by the key. 
LinkedHashMap preserves the insertion order. 
Hashtable is synchronized, in contrast to HashMap.

