#  Largest Rectangle in Histogram
- Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

## Solution
### O(N^2)
- Used two pointer to find the two boundry (which is smaller than the center )
```
          O
        O O
O     O O O
O   O O O O
O O O O O O O
3 1 2 3 4 5 1
      
    |     | 

    O O O O
    O O O O    
```

### Optimize - Stack (When poll, When Offer)
- Repeatedly Check if 2 > 1, dupilcate works.
- Left Pointer move back could know the result from the last number's right Pointers
- Used Stack to scanner from left to right.
- What should I save?
* If only save 3, we couldn't calculate the Area.
* Need to save index of the pointer, then we could use the different between indexs to count the length
``` 
Stack of Index
cur > stack.peek() ---> Offer 現在這塊可以包含前面的面積
cur <= stack.peeK() ---> Continuously Poll 往前結算 - > poll out stack.peek -> calculate area
右邊界：下個存不進來的
左邊界：上個比她小的

```
* Don't forget:
```java
int leftBound = stack.isEmpty()? 0 : stack.peekLast() + 1;
```

## Demo
```
height [3, 1, 2, 3, 4, 5, 1]
index   0  1  2  3  4  5  6  0 <- for the corner case (met the last number)
```
- -> pointer : 0
```
curVal = height[0] = 3
stack.isEmpty() -> offerLast(i)
 ________
| 0
 --------
 ```
 - -> pointer : 1
 ```
curVal = height[1] = 1
stack.peek -> 0 = height[stack.peek] -> 3 > curVal : couldn't count in 
!stack.isEmpty() -> pollLast()
 ________
| 
 --------

 L                      R      Height                  Area
 0 : stack.peek + 1     i      hieght[pollLast()]
 0                      1       3                      = 3
```

- -> pointer : 2
```
curVal = height[2] = 2
stack.isEmpty() -> offerLast(i)
 ________
| 2
 --------

 L                      R      Height                  Area
 0 : stack.peek + 1     i      hieght[pollLast()]
 0                      1       3                      = 3
```

- -> pointer : 3
```
curVal = height[3] = 3
stack.isEmpty() -> offerLast(i)
 ________
| 2 3
 --------

 L                      R      Height                  Area
 0 : stack.peek + 1     i      hieght[pollLast()]
 0                      1       3                      = 3
```

-  -> pointer : 4
```
curVal = height[4] = 4
stack.isEmpty() -> offerLast(i)
 ________
| 2 3 4
 --------

 L                      R      Height                  Area
 0 : stack.peek + 1     i      hieght[pollLast()]
 0                      1       3                      = 3

```
-  -> pointer : 5
```
curVal = height[5] = 5
stack.isEmpty() -> offerLast(i)
 ________
| 2 3 4 5
 --------

 L                      R      Height                  Area
 0 : stack.peek + 1     i      hieght[pollLast()]
 0                      1       3                      = 3
```
-  -> pointer : 6
```
curVal = height[6] = 1
stack.peek -> 5 = height[stack.peek] -> 5 > curVal : couldn't count in 
!stack.isEmpty() -> pollLast()
 ________
| 2 3 4 
 --------

 L                      R      Height                  Area
 0 : stack.peek + 1     i      hieght[pollLast()]
 0                      1       3                      = 3
 5                      6       5                      = 5
```

```
 ***** Need to clear the Stack
```
-   -> pointer : 6
```
curVal = height[6] = 1
stack.peek -> 4 = height[stack.peek] -> 4 > curVal : couldn't count in 
!stack.isEmpty() -> pollLast()
 ________
| 2 3 
 --------

 L                      R      Height                  Area
 0 : stack.peek + 1     i      hieght[pollLast()]
 0                      1       3                      = 3
 5                      6       5                      = 5
 4                      6       4                      = 8
```

-    -> pointer : 6
```
curVal = height[6] = 1
stack.peek -> 3 = height[stack.peek] -> 3 > curVal : couldn't count in 
!stack.isEmpty() -> pollLast()
 ________
| 2  
 --------

 L                      R      Height                  Area
 0 : stack.peek + 1     i      hieght[pollLast()]
 0                      1       3                      = 3
 5                      6       5                      = 5
 4                      6       4                      = 8
 3                      6       3                      = 9
```

-  -> pointer : 6
```
curVal = height[6] = 1
stack.peek -> 2 = height[stack.peek] -> 2 > curVal : couldn't count in 
!stack.isEmpty() -> pollLast()

 ________
| 
 --------

 L                      R      Height                  Area
 0 : stack.peek + 1     i      hieght[pollLast()]
 0                      1       3                      = 3
 5                      6       5                      = 5
 4                      6       4                      = 8
 3                      6       3                      = 9
 2                      6       2                      = 8

```
-    -> pointer : 6
```
curVal = height[6] = 1
stack.peek -> 2 = height[stack.peek] -> 2 > curVal : couldn't count in 
stack.isEmpty() -> offerLast(i)
 ________
| 6
 --------

 L                      R      Height                  Area
 0 : stack.peek + 1     i      hieght[pollLast()]
 0                      1       3                      = 3
 5                      6       5                      = 5
 4                      6       4                      = 8
 3                      6       3                      = 9
 2                      6       2                      = 8
```

- -> pointer : 7 (which is set to curVal = 0)
```
curVal = height[7] = 0
stack.peek -> 6 = height[stack.peek] -> 1 > curVal : couldn't count in 
!stack.isEmpty() -> pollLast()
 ________
| 
 --------

 L                      R      Height                  Area
 0 : stack.peek + 1     i      hieght[pollLast()]
 0                      1       3                      = 3
 5                      6       5                      = 5
 4                      6       4                      = 8
 3                      6       3                      = 9
 2                      6       2                      = 8
 0                      6       1                      = 6
```
 ```
 Max = 9
 ```

- curVal = i == height.length ? 0 : height[i]
- if stack.isEmpty() -> offerLast in Stack
- if !stack.isEmpty() -> calculate the Area:
* curHeight = hieght[the one we pullLast]
* right boundry = i
* left boundry = one we pullLast or the peek in the stack + 1
* If we pullLast the last number in the stack give a left boundry value 0
* -> int leftBound = stack.isEmpty() ? 0 : stack.peekLast() + 1;
- At meanwhile, tracking the max value as wll.

```java
        Deque<Integer> stack = new ArrayDeque<>(); // Store the index
        int max = 0;
        for(int i = 0; i <= height.length; i++) {
            // Each element will be push once and poll once
            // 1. Check whether this element can be pushed into the stack
            int curVal = i == height.length ? 0 : height[i]; // ?????
            // set curVal to the min value to guarantee the last element to be put into stack
            
            while(!stack.isEmpty() && height[stack.peekLast()] >= curVal) {
                int curHeight = height[stack.pollLast()];
                //Find Left and Right Boundary from current rectangle
                int leftBound = stack.isEmpty() ? 0 : stack.peekLast() + 1; // number in stack means the smaller boundary
                int rightBound = i;
                max = Math.max(max, curHeight * (rightBound - leftBound));
            }
            // 2. Push the element into the stack 
            stack.offerLast(i);
        }
        return max;
}
```
