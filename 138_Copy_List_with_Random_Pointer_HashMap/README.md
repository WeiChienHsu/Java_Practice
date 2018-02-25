# Random Pointer

## A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.Return a deep copy of the list.

## Solution - HashMap

- If we need to copy a pointer, we need to record both pointer and the number it points to
* First Pass: copy node and next pointer
* Second Pass: Random pointer
* But We didn't reocrd where the Random pointer points to 

### Used a HashMap to record Original node and new Node
- Time : O(2n)
- Spae : O(n)
- First Pass: Create Nodes after checking
- Second Pass: Link Node
```
O  ->  O  ->  O  ->  O ->
|      |      |      |
O  ->  O  ->  O  ->  O ->
```


## My two padd Record way (Deep Copy)
- Copy all nodes into the Map <old, Copied> (Which we needed to new a node into Heap)
- Find copied in the Map and connect both "next" and "random" together
- Used a dummy node to record the result
- Return the dummy Node



### One Pass to Record both Node and Random copy
- Add in: Check if Node is already in the Map <Original Node, Copy Node>
- 1. Add (cur, curCopy) -> Check if it's already copied
```java
if(!map.containsKey(cur)) {
    RandomListNode curCopy = new RandomListNode(cur.label);
    map.put(cur, curCopy);
}
```
- 2. Add (cur.next, nextCopy) -> Check if its next pointer has already been copied
```java
if(cur.next != null && !map.containsKey(cur.next)) {
    RandomListNode nextCopy = new RandomListNode(cur.next.label);
    map.put(cur.next, nextCopy);
```
- 3. Add (cur.random, randomCopy) -> Check if its random pointer has already been copied
```java
if(cur.random != null && !map.containsKey(cur.random)) {
    RandomListNode randomCopy = new RandomListNode(cur.random.label);
    map.put(cur.random, randomCopy);
```
- Connect: Connect curCopy with nextCopy and randomCopy
- 1. map.get(cur).next = map.get(cur.next)
```java
if(cur.next != null) {
    map.get(cur).next = map.get(cur.next);
}
```
- 2. map.get(cur).random = map.get(cur.random)
```java
if(cur.random != null) {
    map.get(cur).random = map.get(cur.random);
    }
```



```java
 Map<RandomListNode, RandomListNode> map = new HashMap<>();
RandomListNode cur = head;        

```
```java
if(!map.containsKey(cur)) {
    RandomListNode curCopy = new RandomListNode(cur.label);
    map.put(cur, curCopy);
}

if(cur.next != null && !map.containsKey(cur.next)) {
    RandomListNode nextCopy = new RandomListNode(cur.next.label);
    map.put(cur.next, nextCopy);
}

if(cur.random != null && !map.containsKey(cur.random)) {
    RandomListNode randomCopy = new RandomListNode(cur.random.label);
    map.put(cur.random, randomCopy);
}
```
- Connect copy current Node with Copy Random and Next
```java
if (cur.next != null){
    // curCopy.next = nextCopy
    map.get(cur).next = map.get(cur.next);
}
    // curCopy.random = randomCopy
if(cur.random != null) {
    map.get(cur).random = map.get(cur.random);
}
// Next round
cur = cur.next;
}
```