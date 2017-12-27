# 11 Container wiht Most Water - Two pointers Question

## Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

```
                       |
     |              |  |      
     |  |     |     |  |  |   
  |  |  |  |  |  |  |  |  |   
  |  |  |  |  |  |  |  |  |  |
  2  4  3  2  3  2  4  5  3  1

```

## Solution: two pointers from left and right
- Since the contains ammount depends on the lower side, such as the rightest 2 and the leftest 1, no mather how 2 move, 2 and 1 could have the max ammount.


```
                       |
     |              |  |      
     |  |     |     |  |  |   
  |  |  |  |  |  |  |  |  |   
  |  |  |  |  |  |  |  |  |  |
  2  4  3  2  3  2  4  5  3  1

  Min(2,1) * dist(2,1)
  Min(2,3) * dist(2,3)
  Min(4,3) * dist(4,3)
  Min(4,5) * dist(4,5)
    ......
Give up the smaller side, record the max num

```
- Min(left,right) * Dist (left, right) = Max contains
- Thus, we count the max value comparing with other pairs and move forward or back depends on if left is greater then right.

```java
while (start < end) {
    // dist(start, end) * min(start, end)
    max = Math.max(max, (end - start) * Math.min(height[end], height[start]) );
    if (height[start] > height[end]) {
        end --;
    } else {
        start ++;
    }
}
return max;
}
```