# 213 House Robber II

## From
[198 House Robber](https://github.com/WeiChienHsu/Java_Practice/tree/master/198_House_Robber)

### After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

# Soluction

- Separate the subproblems to analyze situation of rubbing the first one and not rubbging the first (which means rubbing the last one)

```
   -  1 - 
 /        \
 5         4
  \       / 
   3  -  4  
```

- Rob 1 -> no 5 -> Max: 1 + 4 = 5
- Rob 5 -> no 1 -> Max: 5 + 4 = 9 
- no 5/1 - > (4 - 4 - 3)  Same as above
- We only need to see those TWO conditions

```java
private static int subRob(int[] n, int start, int last){
    int prev = 0;
    int current = n[start];

    for(int i = start + 1; i <= last; i++) {
        int next = Math.max(current, prev + n[i]);
        prev = current;
        current = next;
    }

    return current;
}
```

- To find the Math in these two conditions
```java
return Math.max(subRob(nums, 0, nums.length - 2), subRob(nums, 1, nums.length - 1));
```