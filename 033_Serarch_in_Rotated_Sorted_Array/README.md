#  Search in Rotated Sorted Array
- Binary Search

## Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

```
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
```

## Always find the mid in Binary Search
```java
int mid = start + (end - start) / 2;
```

## Locate Nums[mid] + Locate Target
- arr[mid] > arr[start]: means to LeftSide
*  target >= arr[start] && target < arr[mid] : Left
*  Remove Right : end = mid

- arr[mid] < arr[start]: means to RightSide
*  target > nums[mid] && target <= nums[end] : Right
*  Remove Left : start = mid

```java
if(nums[mid] > nums[start]) { // left
    if(target >= nums[start] && target < nums[mid]) {
        end = mid; // target in left, remove right
    } else {
        start = mid;
    }
} else{ // nums[mid] < nums[start] : Right
    if(target > nums[mid] && target <= nums[end]) {
        start = mid;
    } else {
        end  = mid;
    }
```
- Return either start or end (or null), since we give a while condition that won't let start and end touch each other.
```java
 while (start + 1 < end) {
```
```java
    if(nums[start] == target) return start;
    if(nums[end] == target) return end;
    return -1;
```

## Solution II - Compare with the End
- 4 5 6 7 0 1 2 : num[mid] > num[end]
```
if(target > num[mid] || target < num[end]) -> in the right side -> start = mid + 1
else - > in the left side -> end = mid
```

- 5 6 7 0 1 2 3 : num[mid] < num[end]
```
if(target > num[mid] && target < num[end]) -> in the right side -> start = mid + 1
else -> in the right side -> end = mid
```

```java
while(start < end) {
    mid = start + (end - start) / 2 ;
    if(nums[mid] > nums[end]) { // 4 5 6 7 0 1 2
        if(target > nums[mid] || target <= nums[end]) {
            start = mid + 1;
        } else {
            end = mid;
        }
    }  
        if(nums[mid] < nums[end]){ // 5 6 7 0 1 2 3
            if(target > nums[mid] && target <= nums[end]){
                start = mid + 1;
            } else {
                end = mid;
            }
    }
}
```
