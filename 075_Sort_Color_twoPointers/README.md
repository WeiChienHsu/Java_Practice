# Sort Color
### Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

```
2 0 1 2 0 1
->
0 0 1 1 2 2
```

## Solution - One Path
```
 2   0   1   2   0   1  
 l                   r
 s

 meet 2  -> switch with r / r move left / s--

 1   0   1   2   0   2  
 l               r
s

 s++ -> meet 1 -> move on 

  1   0   1   2   0   2 
  l               r
      s

 meet 0 -> swith with l / l move right / s ++

  0   1   1   2   0   2
      l           r
          s

meet 1 -> move on 

  0   1   1   2   0   2
      l           r
              s

 meet 2  -> switch with r / r move left / s--

  0   1   1   0   2   2
      l       r
           s    

s++ -> meet 0 -> swith with l / l move right / s ++

 0   0   1   1   2   2
         l   r
             s 


```
- Try to separate them into three groups (subArray)
- Use Two pointers (left&right) and one scanner
```java
int left = 0; //Left boundary of processed 0
int right = nums.length -1; // Right boundary of processed 2
int cur = 0; // Scanner for un processed data
```


- If meet 2, switch the number of scanner with right pointer and move left the right pointer
* Need to move backforward scanner since we didn't sure if the right pointer pointing to a value which is smaller than 1
```java
if(nums[cur]  == 2) {
    nums[cur] = nums[right];
    nums[right] = 2;
    right --;
    cur --;
```


- If meet 1, just move on
```java
cur ++;
```

- If meet 0, swith the number of scanner with left pointer and move right the left pointer
* Now we don't need to move back scanner since the numbers behind it are always be 0
```java
else if(nums[cur] == 0) {
    nums[cur] = nums[left];
    nums[left] = 0;
    left ++;
} 
```

