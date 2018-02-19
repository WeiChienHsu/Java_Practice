# Level Traversal - Only Record the Rightest node
```
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

```
- Output: [1,3,4]

## Solution

- Used LastValue to record each rightest node
```java
int size = queue.size()
int lastVal = 0;
for(int i = 0 ; i < size ; i++) {
  .....
}
res.add(lastVal);
```
