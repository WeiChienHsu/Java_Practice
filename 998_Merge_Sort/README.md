# Merge Sort - Recurision
- Time : O(nlogn) by the heigh of tree (DFS)
- Merge time in each evel: O(n)
- Height == logn
- Space Complexity: O(n)


- doSort (binary search to find mid and separate into two array)
- Merge (used helper arr to copy the didSort array and grab from the smaller number to the end )

```
           38  27  43  3  9  10  80
                      / \
        38 27 43 3         9  10  80
            / \              /  \
    38 27      43  3      9  10    80
     / \         / \       / \     /
   38    27    43     3   9   10   80
   |---------a--------|   |----b----|

   
```

- Keeping finding the middle value on an array.
- Give a helper integer array to record all value.
- Until you separate each number into the end.
- The end point is while start = end, thus you return the value, Recurision will be end and into the merge period.


```java
   private static void doSort(int[] arr, int[] helper, int start, int end) {
        // End the recursion
        if(start >= end) return;

        int mid = start + (end - start) / 2;
        // Sort left side
        doSort(arr, helper, start, mid);
        // Sort right side
        doSort(arr, helper, mid + 1, end);

        merge(arr, helper, start, mid, end);
    }
```


- Then, merge all separate number together by comparing two final arraies with each other.
- By using aCur pointer and bCur pointer to choose the less number pushing into array.
- You might face four situtions
* Chosen all a area numbers already.
* Chosen all b area numbers already.
* Pointed number in a > b.
* Pointed number in b > a.

```java
private static void merge(int[] arr, int[] helper, int aStart, int aEnd, int bEnd) {
    // Copy arr from aStart to bEnd
    for (int i = aStart; i <= bEnd; i++ ) {
        helper[i] = arr[i];
    }

    int aCur = aStart;
    int bCur = aEnd + 1;

    for(int i = aStart; i <= bEnd; i++ ) {
        if (aCur > aEnd){
            // use out a
            arr[i] = helper[bCur++];
        } else if (bCur > bEnd){
            // use out b
            arr[i] = helper[aCur++];
        } else if (helper[aCur] <= helper[bCur]) {
            // a < b
            arr[i] = helper[aCur++];
        } else {
            arr[i] = helper[bCur++];
        }

    }
```
-  


