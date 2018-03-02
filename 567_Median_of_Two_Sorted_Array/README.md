# Median of Two Sorted Array (Log(m+n))
- 如何取得 k/2 的數，將它去除，這樣就可以用O(1)的時間複雜度，完成
- 可能扔除 A的前k/2個數，或是 B前K/2的數
- 比較A,B第K/2 - 1 個數，留下比較大的那個數，丟棄較小的不會丟到目標（因為目標比他大）
```java
  if(A_key < B_key) {
      return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
  } else {
      return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
  }

```
- 丟掉之後，剩下來要找的是 k - k/2 的數，再丟入下一輪繼續找 k /2 的數

```java

        
  if(A_key < B_key) {
      return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
  } else {
      return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
  }

```
- 如果A數組不夠了，假設後面的數都是無限大，這樣一定會留下A數組
- (因為可能遇上A只有兩個數，B非常非常長，就假設A後面都是無限大)
- 如果 K 等於一的話，回傳比較小的那個值，就會是第k個數
```java
  if(k == 1) {
      return Math.min(A[A_start], B[B_start]);
  }
  
  int A_key = A_start + k / 2 - 1 < A.length ? A[A_start + k / 2 - 1] : Integer.MAX_VALUE;
  int B_key = B_start + k / 2 - 1 < B.length ? B[B_start + k / 2 - 1] : Integer.MAX_VALUE;

```

- 一開始要判斷一下兩個數組的中間數，奇偶數有影響
```java
        int len = nums1.length + nums2.length;
        if(len % 2 == 1) {
            return (findKth(nums1, 0, nums2, 0, len / 2 + 1));
        }
        
        return (findKth(nums1, 0, nums2, 0, len / 2) + findKth(nums1, 0, nums2, 0, len / 2 + 1)) / 2.0;
```

