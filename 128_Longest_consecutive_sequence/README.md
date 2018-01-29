#  Longest Consecutive Sequence

## Solution 

- 將Array內的數字依序加入Map中，Map的Key是數字，Value是出現的次數。
- 檢查該數字的左鄰居與右鄰居，是否在Map當中，如果有的話 left & right 的值將會改變。
- left and right 的數值取決於他們在Map中所記錄的長度。
- 總和的長度為 right  +  left  +  1 
- 更新總和的最大長度
- 更新Map中左右鄰居的長度（假如Map中有1&3，掃到2，2的長度變為 1 + 1 + 1 = 3，同時，1&3的長度應該更新為 3 3。
- 更新（n - left) && (n-right)，因為要改變的是邊界值，而不是鄰近值！！！！

```
{1,3,2}
Map
  |  |  |
---------
  |  |  |

1:
left - to check if 0 in the map, no, left = 0
right - to check if 2 in the map, no, right = 0
total length = 0 + 0 + 1(1 itself) = 1
->>>>Put 1 and total length 1 into Map
longest seq = Max(res, total length) = 1
Change the value of (1 - left) and (1 - right) -> Which means the number nearby 1

Map
 1 |  |  |
----------
 1 |  |  |

```

```
Map
 1 | 3 |  |
----------
 1 | 1 |  |

3:
left - to check if 2 in the map, no, left = 0
right - to check if 4 in the map, no, right = 0
total length = 0 + 0 + 1(3 itself) = 1
->>>>Put 3 and total length 1 into Map
longest seq = Max(res, total length) = 1
Change the value of (1 - left) and (1 - right) -> Which means the number nearby 1

 ```

 ```
Map
 1 | 3 | 2 |
------------
 1 | 1 | 3 |

2:
left - to check if 1 in the map, yes, get the length: left = 1
right - to check if 3 in the map, no, get the length: right = 1
total length = 1 + 1 + 1(2 itself) = 3
->>>>Put 2 and total length 3 into Map
longest seq = Max(res, total length) = 1
Change the value of (1 - left) and (1 - right) -> Which means the number nearby 1


 1 | 3 | 2 |
------------
 3 | 3 | 3 |
 ```

- See if n-1 and n+1 exist in the map, and if so, it means there is an existing sequence next to n.

- Give a variable left and right to calculate the length of those two sequence(Check if it was in the map)

```java
int left = (map.containsKey(num - 1))? map.get(num - 1) : 0;
int right = (map.containsKey(num +1))? map.get(num + 1) : 0;
```

- Used those left and right to locate the other end of the sequence to the left and right of n respextively 
```java
int sum = left + right + 1;
map.put(num,sum);
res = Math.max(res, sum);
```

- Replace the vaue with the new length
```java
map.put(num - left, sum);
map.put(num + right, sum);
```