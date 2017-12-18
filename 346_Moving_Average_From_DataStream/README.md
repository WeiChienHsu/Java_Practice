# Moving Average from Data Stream

## Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

```
For example,
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3

```

## Solution

- Initize a MovingAverage used constructor put a value of size and sum
- Uesd Queue since we need to pull out number when the limited size is reached
```java
public MovingAverage(int size) {
    this.queue = new ArrayDeque<>();
    this.sum = 0;
    this.size = size;
}
```


- If met the limited size, pull out the first number:
```java
if(queue.size() == size) {
    int last = queue.pullFirst();
    sum -= last;
}
```


- If there is enough space, offer the last space and count the average
```java
queue.offerLast(val);
sum += val;
return sum * 1.0 / queue.size();
```