import java.util.Arrays;
import java.util.HashMap;

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

class LRU {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        int[] result = new int[5];

        cache.put(1, 1);
        cache.put(2, 2);
        result[0] = cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        result[1] = cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        result[2] = cache.get(1);       // returns -1 (not found)
        result[3] = cache.get(3);       // returns 3
        result[4] = cache.get(4);       // returns 4

        System.out.println(Arrays.toString(result));
    }

}

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