# Permutations

## Given a collection of distinct numbers, return all possible permutations.

```
For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```
# Solution: SWAP
- Fix a position for going down
- Change the original array 
- Before going into recursion, we need to swap for fixing a position 
- After recursion, swap back to free a position for going right


```java
  for(int i = pos; i < nums.length; i++) {
      list.add(nums[i]);
      swap(nums, pos, i);
      helper(nums, list, res, pos + 1);
      swap(nums, pos, i);
      list.remove(list.size() - 1);
  }
```
- Base code
```java
  if(pos == nums.length) {
      res.add(new ArrayList<Integer>(list));
      return;
  }
```

```
pos *

     *[1]               1  2   3
     /   \              i
 [1,2]   [1,3]            list[1]
   |        |
[1,2,3]   [1,3,2]    

helper(nums, list, res, pos + 1);

      [1]               1  2   3
     /   \                 i
*[1,2]   [1,3]            list[1,2]
   |        |
[1,2,3]   [1,3,2]    

helper(nums, list, res, pos + 1);

      [1]               1  2   3
     /   \                     i
 [1,2]   [1,3]            list[1,2,3]
   |        |
*[1,2,3]   [1,3,2] 


(pos == nums.length) -> back -> res.add(list)


      [1]               1  2   3
     /   \                 i
* [1,2]   [1,3]            list[1,2,3]
   |        |
 [1,2,3]   [1,3,2] 

 helper(nums, list, res, pos + 1);

       [1]               1  2   3
     /   \                      i
* [1,2]   [1,3]            list[1,3]
   |        |
 [1,2,3]   [1,3,2] 

swap (nums, pos = 1, i = 2) -> 1 3 2

       [1]               1  3  2
     /   \                     i
  [1,2]   [1,3]            list[1,3, 2]
   |        |
 [1,2,3]  *[1,3,2] 

(pos == nums.length) -> back -> res.add(list)
swap (nums, pos = 1, i = 2) -> 1 2 3

       [1]               1  2  3
     /   \                     i
  [1,2]  *[1,3]            list[1,3]
   |        |
 [1,2,3]   [1,3,2] 

 (pos == nums.length) -> back

      *[1]               1  2  3
     /   \                     i
  [1,2]   [1,3]            list[1]
   |        |
 [1,2,3]   [1,3,2] 

 ```