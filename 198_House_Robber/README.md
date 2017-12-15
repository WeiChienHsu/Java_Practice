# 198. House Robber

## You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

###  Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

## Solution 

- Draw a Strategy Tree to find how max[0] -> max[4]

- 1-4-4-9 

```
 ↙r              0
 /no-r        ↙      \
1            1        0
            /         ↙ \
4          1         4   0
          ↙ \       /   ↙ \
4       5    1     4   4   0
       /    ↙ \   ↙ \   \  ↙\
9     5    10  1 13 4   4 9  0
```

- Remove the impossible branches
```
 ↙r              0
 /no-r        ↙      \
1            1*       0
            /         ↙ 
4          1         4*   
          ↙         /  
4       5*        4   
       /         ↙   
9     5       13*  

```

- Then we could get a Induction Rule
```
max[i+1] = Math.max(max[i],max[i-1] + money[i])
```

- To Optimize the Space Complexity
```
next = Math.max (current, money[i]+previous)
```
- Check the corner value first
```java
if(nums == null || nums.length == 0) {
    return 0;
} else if(nums.length == 1){
    return nums[0];
}
```

- Run the Rule
```java
int prev = 0;
int current = nums[0];

for(int i = 1; i < nums.length; i++) {
    int next = Math.max(current, prev + nums[i]);
    prev = current;
    current = next;
}

return current;
}
```
