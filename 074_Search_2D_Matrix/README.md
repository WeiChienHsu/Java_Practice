# Search a 2D Matrix

## Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

```
Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]

```

## Solution: Mine
- Search for the last number in each array
* If target is larger then the last array's last number -> return false
* If target is larger then i array's last number but smaller than i+1 's -> deal with the i th array
- Save i array out to an ArrayList and use left and right pointer to find the number
* while (start <= end)
* if target = array[mid] -> return true
* if target < array[mid] -> mid - 1 = end
* if target > array[mid] -> mid + 1 = start

## Solution 2D -> 1D & 1D -> 2D
- See a whoe matrix as an array
- 2D -> 1D
```
(i,j) - index = i*n +j
```
- 1D -> 2D
```
index(i,j) -> 

i = index/n
j = index%n
```
