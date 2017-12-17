# 134 Gas Station

## There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

```
You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
```

### First Thought - Starting from every point
- Not Nature, it takes O(n^2)

### Second Thought - Queue
- We onlu care about "gas_left_if_move"
- Add new Node if there is enough gas
- Remove the first Node if there is no enough gas
- Queue: First in first out

#### Disadvantage : We repeatedly add in old Node and Remove it.

### Third Thought - Deque
- Deque could add and remove in both head and tail
- move the start point in the last index
- move the end point in the first index
```java

int start = gas.length - 1;
int end = 0;
int sum = gas[start] - cost[start];
```

- If there is enough gas -> count the remain gas -> move on -> end++
```java
if(sum >= 0) {
    sum += gas[end] - cost[end]; // count the remain gas
    end++  ; // keep move on
```
-> Precise
```java
 sum += gas[end] - cost[end++];
```

- If there is no enough gas -> borrow the gas -> count the new remian gas
```java
else { // Not enough Gas
    start--  ; // brower gas from last gas station
    sum += gas[start] - cost[start];
```
-> Precise
```java
 sum += gas[--start] - cost[start];
```
- Finally, return the start point if sum > 0
```java
return sum >= 0 ? start : -1;
```