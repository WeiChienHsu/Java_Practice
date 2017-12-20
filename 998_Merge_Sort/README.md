# Merge Sort

- Keeping finding the middle value on an array.
- Give a helper integer array to record all value.
- Until you separate each number into the end.
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
- Recurision 

```
           38  27  43  3  9  10  80
                      / \
        38 27 43 3         9  10  80


```
