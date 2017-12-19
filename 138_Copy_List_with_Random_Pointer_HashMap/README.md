# Random Pointer

## A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.Return a deep copy of the list.

## Solution - HashMap
- Save current node, current next and current random in HashMap
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