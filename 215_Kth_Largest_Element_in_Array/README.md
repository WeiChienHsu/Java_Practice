# Kth Largest Element in an Array

## Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

```
For example,
Given [3,2,1,5,6,4] and k = 2, return 5.
```

## Solution - MergeSort
- Time Complexity O(K + nlogn)
- We Only need to sort the largest K element! Dont waste time to sort the rest array.

## Solution - minHeap
- Time Complexity : O((n-k) logk + k)


- Keep tracking the most kth largest Element
- Always offer the largest number, and poll out the min number
- Using minHeap
* First: heapify O(k)
* Poll/Push an element: (re-heapify) O(logk)
- Last : Poll the top elements

```
-2  0  1  0  2  3  -3  -2  1 

Heap:
-2 0 0 1 2

input -> 3

Heap:
0 0 1 2 3

input -> -3

Heap:
0 0 1 2 3

input -> -2

Heap:
0 0 1 2 3

input -> 1

Heap:
0 1 1 2 3
```

## Solution - Quick Select
- Randomly Select an Element
```
 s                                   e
 -2   0   1   0   2   3   -3    -2   1
 pos                                pivot
 a[i]
```

- arr[i] <= pivot  -> pos++, i++  -> swap(pos,i)
- arr[i] > pivot -> pos, i++

* -2 < 1 -> pos++, i++
```
 s                                   e
 -2   0   1   0   2   3   -3    -2   1
     pos                             pivot
     a[i]
```

* 0 < 1 -> pos++, i++
```
 s                                   e
 -2   0   1   0   2   3   -3    -2   1
         pos                         pivot
         a[i]
```
* 1 <= 1 -> pos++, i++
```
 s                                   e
 -2   0   1   0   2   3   -3    -2   1
             pos                     pivot
             a[i]
```
* 2 > 1  -> pos, i++
```
 s                                   e
 -2   0   1   0   2   3   -3    -2   1
                  pos                pivot
                      a[i]
```

* 3 > 1  -> pos, i++
```
 s                                   e
 -2   0   1   0   2   3   -3    -2   1
                  pos                pivot
                          a[i]
```

* -3 < 1  ->  i++  
```
 s                                   e
 -2   0   1   0   2   3   -3    -2   1
                  pos                pivot
                           a[i]
```

* -3 < 1  -> swap(pos,i)
```
 s                                   e
 -2   0   1   0   -3   3    2    -2   1
                  pos                pivot
                           a[i]
```

* -3 < 1  -> pos++
```
 s                                   e
 -2   0   1   0   -3   3    2    -2   1
                       pos            pivot
                           a[i]
```

* -2 < 1  ->  i++
```
 s                                   e
 -2   0   1   0   -3   3    2    -2   1
                       pos            pivot
                                 a[i]
```

* -2 < 1  ->  swap(i,pos)
```
 s                                   e
 -2   0   1   0   -3   -2    2     3   1
                       pos            pivot
                                 a[i]
```

* -2 < 1  ->  pos++
```
 s                                   e
 -2   0   1   0   -3   -2    2      3    1
                            pos          pivot
                                    a[i]
```

* 1 <= 1  ->  i++
```
 s                                   e
 -2   0   1   0   -3   -2    2      3    1
                            pos          pivot
                                         a[i]
```

* 1 <= 1  ->  swap
```
 s                                   e
 -2   0   1   0   -3   -2    1      3    2
                            pos          pivot
                                         a[i]
```

- Find the pos 1 is the 3th largest
- We find the 2th largest number in the rest left array
- Worse Case : Always pivot the largest one : O(N^2`)
- Average: Half of the array left for next level : O(n)

```java

public int kLargeR(int[] nums, int start, int end, int k) {
        if(start > end) {
            return Integer.MIN_VALUE;
        }
        int pivot = nums[end];
        int pos = start;

        for(int i = start; i < end ; i++) {
            if(nums[i] <= pivot) {
                swap(nums,pos++,i);
            }
        }

        swap(nums,pos,end); // set pivot to the partition position

        if(pos == k) {
            return nums[pos];
        } else {
            return pos < k ? kLargeR(nums, pos + 1 , end, k) : kLargeR(nums, start, pos - 1 , k);
        }
    }

```