#  Kth Smallest Element in a Sorted Matrix
## Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

```
Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.

```

## Solution - minHeap
- Use minHeap to record Point we defined, it would let the min Point go to the peek
```
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]

            (0,0, 1)
           /        \
    (0,1, 5)      (1,0, 10)
      /  \           /  \
  (0,2,9) (1,1,11) (1,1,11) (2,0,13)
```
- Define Potin with x, y and val
```java
class Point {
    int x;
    int y;
    int val;

    public Point(int x, int y, int val){
        this.x = x;
        this.y = y;
        this.val = val;
    }
}
```

- Make a Comparator to Point 
* o1.val > o2.val return 1, then will have decend sorted
```java
class myComparator implements Comparator<Point> {
    @Override
    public int compare(Point p1, Point p2) {
        if(p1.val == p2.val) return 0;
        return p1.val - p2.val;
    }
}
```

- minHeap + ProirityQueue, always poll out the min one
```java
 PriorityQueue<Point> minHeap = new PriorityQueue<>(k, new myComparator());
```
- boolean[][] to record visited Point
```java
int row = matrix.length;
int col = matrix[0].length;
int res = Integer.MIN_VALUE;
boolean[][] visited = new boolean[row][col];
minHeap.offer(new Point(0,0,matrix[0][0]));
visited[0][0] = true;
```

- Move right and move down to check if the point has been visited
- Poll out the Min Point(val with matrix[x][y]) from Heap and count as current point
- Count K number than return the Result     


* if Not add into the minHeap
```java
while(k > 0) {
    Point cur = minHeap.poll();
    int curX = cur.x;
    int curY = cur.y;
    if(curX + 1 < row && !visited[curX +1][curY]) {
        minHeap.offer(new Point(curX+1, curY, matrix[curX+1][curY]));
        visited[curX+1][curY] = true;
    }

    if(curY + 1 < col && !visited[curX][curY+1]) {
        minHeap.offer(new Point(curX, curY+1, matrix[curX][curY+1]));
        visited[curX][curY+1] = true;
    }

    res = cur.val;
    k--;
    }
        return res;
    }
}
```

## Solution- Binary Search

```
0  0  2  3
1  1  2  4
2  3  3  5
2  3  4  5

k = 10
```

### Binary Search for the target number
- Use the Binary search to find mid number from the smallest to the largest
* Which means (0+5)/2 = 2 in this case.

- Find how many numbers are there be equal and less than 2
* if < 10, find the (2+5)/2 = 3 in the next step
* if > 10, find the (0+2)/2 = 1 in the next step

### notLarger function
- How we run in the Matrix
* if less than or equal to 2, means the number left all less and equal to 2
* count the all left number and move down the pointer
* matrix[i][j] <= 2  -> i++ and count += j+1

* if larger than 2, means the number left is not sure if less or larger than 2
* We move the pinter left 
* matrix[i][j] > 2  -> j--

### Demo of Finding 2
```
0  0  2  3*
1  1  2  4
2  3  3  5
2  3  4  5

k = 10
count = 0
```
3 > 2 move left

```
0  0  2* 3
1  1  2  4
2  3  3  5
2  3  4  5

k = 10
count = 3
```
2 <= 2 move down / count += 2+1

```
0  0  2  3
1  1  2* 4
2  3  3  5
2  3  4  5

k = 10
count = 6
```
2 <= 2 move down / count += 2+1

```
0  0  2  3
1  1  2  4
2  3  3* 5
2  3  4  5

k = 10
count = 6
```
3 > 2 move left

```
0  0  2  3
1  1  2  4
2  3* 3  5
2  3  4  5

k = 10
count = 6
```
3 > 2 move left

```
0  0  2  3
1  1  2  4
2* 3  3  5
2  3  4  5

k = 10
count = 7
```
2 <=  2 move down / count += 1

```
0  0  2  3
1  1  2  4
2  3  3  5
2* 3  4  5

k = 10
count = 8
```
2 <=  2 move down / count += 1

```
0  0  2  3
1  1  2  4
2  3  3  5
2  3  4  5
*
k = 10
count = 8
```
end -> i < row && j >= 0


### Demo of Finding 3 : Since we got 8 number smaller than and equal to 2
```
0  0  2  3*
1  1  2  4
2  3  3  5
2  3  4  5

k = 10
count = 4
```
3 <= 3 move down / count += 3+1

```
0  0  2  3
1  1  2  4*
2  3  3  5
2  3  4  5

k = 10
count = 4
```
4 > 3 move left

```
0  0  2  3
1  1  2* 4
2  3  3  5
2  3  4  5

k = 10
count = 7
```
2 <= 3 move down / count += 2+1

```
0  0  2  3
1  1  2  4
2  3  3* 5
2  3  4  5

k = 10
count = 10
```
3 <= 3 move down / count += 2+1

```
0  0  2  3
1  1  2  4
2  3  3  5
2  3  4* 5

k = 10
count = 10
```
4 > 3 move left

```
0  0  2  3
1  1  2  4
2  3  3  5
2  3* 4  5

k = 10
count = 12
```
3 <= 3 move down / count += 1+1

```
0  0  2  3
1  1  2  4
2  3  3  5
2  3  4  5
   *
k = 10
count = 12
```

### Find <= 2 -> count == 8 ; Find <= 3 -> count == 12 ; return 3!!!!!!