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

##
