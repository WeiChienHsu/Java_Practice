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
# Solution: SWAP - Traversal
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

 ## DFS
- Save a number not exist in the list and go to the next level 
- When meet the base case, return back to the last evel
- Add a number into the list and go to the next level
- When back to the last level, remove one number in the list



* Base Case
```java
  if(list.size() == num.length) {
      res.add(new ArrayList<Integer>(list));
      return;
  }
```

* Condtional move
```java
  for(int i = 0; i < num.length; i++) {
    if(!list.contains(num[i])) {
        list.add(num[i]);
        dfs(num, list, res);
        list.remove(list.size()-1);
    }
}
```


# Permutations 排列問題
和 Combination 不一樣：
- 不需要一個 StartIndex 紀錄已經使用過的值
- 要判斷List裡面是否有放過要放得值（不需要額外使用Set紀錄）

- 遞歸的定義: 找到所有permutation開頭的排列

- 遞歸的拆解: 找所有以1開頭的排列，接著找2開頭的排列，再找3開頭的排列

- 遞歸的出口:當數字全放入List

```

           p =  [ ]
        /       |       \
    [1]        [2]        [3]
   / \         / \        /  \
 [1,2] [1,3] [2,1] [2,3] [3,1] [3,2]   
   |     |    |       |    |      |
 ```

