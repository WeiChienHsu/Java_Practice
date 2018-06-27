# LRU Cache
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

```
get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
```

Follow up:
Could you do both operations in O(1) time complexity?

```
Example:

LRUCache cache = new LRUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
```

## Solution

要設計一款 Least Recently Used Cache (LRU Cache)，讓沒有被使用的程序，在Cache已經滿的時候，成為下次插入時被移除的對象。

1. Cache 本身帶有一個 capacity ， 最多只能放多少個 Key - Value pair 在裡面。

2. 實現一個 put 操作，放入一個 Key - Value pair，檢查是否已經存在 Cache裡面，如果不存在，檢查是否已經滿了，滿了的話，把最沒被使用的那個移除，插入新的，如果已經存在，更新Value並且改變排序，成為最新被使用過的。

```
put(2,2) -> 檢查 Key 2 是否存在 -> 不存在：檢查是否已經達到 capacity 上限 -> 已達上限：將最舊的移除，並插入，排序改成最新的。
                                                                  -> 未達上限：直接插入，排序改成最新的。
                              -> 存在： 更新 Value 成為 2 ， 並且把 (2,2) 的排序改成最新的。
```
3. 實現一個 get 操作：找尋該Key所對應的Value是否存在，如果存在，取出，並且改變該Key-Value Pair 的排序成為最新的，如果不在的話，直接回傳 -1

```
get(2) -> 檢查 Key 2 是否存在 -> 不存在：return -1
                            -> 存在： return value，改變 (2,2) 的排序成為最新的。

```

4. 要求 put 和 get 的操作都是 O(1) 的時間複雜度。

## Analysis

1. 因為有Key-Value pair，並且要以 O(1) 的時間複雜度取得資料，我們得使用一個 HashMap，每個Key指向其對應的 Node Value。
2. 因為要求 O(1) 的時間複雜度 insert，我們可以使用 LinkedList 的數據結構，但是，還要求 delete 也在 O(1)的時間複雜度，所以我們得使用 Double LinkedList，才能用反向的指針，找到需要刪除的Node，前面一個數，進行O(1)的刪除操作。


## Code

```java
class LRUCache {

    // Map with a Key and Value points to our Double LinkedList
    private HashMap<Integer, Node> map;
    private int capacity;
    private Node head;
    private Node tail;

    // Init the LRUCache Instance
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        this.head = null;
        this.tail = null;
    }

    public int get(int key) {
        Node node = map.get(key);

        if(node == null) {
            return -1;
        }

        // If exist check if the node is in the tail
        // If it was not in the tail, we need to update it's position
        if(node != tail) {

            // If it was in the head, move head to the next one
            if(node == head) {
                head = head.next;
            } else {
                // Remove the Node from current position O(1)
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
            // Update the new Tail to this node
            tail.next = node;
            node.pre = tail;
            node.next = null;
            tail = node;
        }

        return node.value;
    }

    /*
     * The New Insert Node will be on the tail and the least one will on the head
     */
    public void put(int key, int value) {
        // To see if key has already existed in the map
        Node node = map.get(key);

        // Key was in the Map
        if(node != null) {
            // If key existed -> Update
            node.value = value;
            // If node was not in the tail (Update it to the tail)
            if(node != tail) {
                // Change the position of our updated Node
                if(node == head) {
                    // If it was in the head, move head to the next one
                    head = head.next;
                } else {
                    // Remove the Node from current position O(1)
                    node.pre.next = node.next;
                    node.next.pre = node.pre;
                }

                // Update the Tail Node to point to our just changed Node
                // and Update the tail to our last Node
                tail.next = node;
                node.pre = tail;
                node.next = null;
                tail = node;
            }
        }
        else {
            // If not exist, check capacity and insert the key and value
            Node newNode = new Node(key, value);
            // If capacity is 0 means its full
            if(capacity == 0) {
                // Remove first element in the List (Head pointed to)
                Node temp = head;
                head = head.next;
                map.remove(temp.key);
                capacity++;
            }
            // If there is no any node in the list
            if(head == null && tail == null) {
                head = newNode;
            }
            else {
                // Update a new Node next to the current Tail Node
                tail.next = newNode;
                newNode.pre = tail;
                newNode.next = null;
            }
            // Update the Tail to point to the current last Node
            tail = newNode;
            map.put(key, newNode);
            capacity--;
        }
    }
}

class Node {
    int key;
    int value;
    Node next;
    Node pre;
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
```