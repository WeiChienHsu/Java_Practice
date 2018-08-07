public class RandomizedSet {
    private Node head;
    private int currentNumber;

    public RandomizedSet() {
        this.currentNumber = 0;
        this.head = null;
    }

    public boolean insert(int val) {
        /* If the set is empty */
        if(head == null) {
            head = new Node(val);
            this.currentNumber++;
        }
        /* Check if the inserted value has already in the set */
        else {
            Node cur = head;
            for(; cur.next != null; cur = cur.next) {
                if(cur.val == val) return false;
            }

            /* cur points on the last node */
            if(cur.val == val) {
                return false;
            } else {
                cur.next = new Node(val);
                this.currentNumber++;
            }
        }
        return true;
    }

    public boolean remove(int val) {
        /* If the set is empty */
        if(head == null) {
            return false;
        } else if(head.val == val) {
            head = head.next;
            this.currentNumber--;
            return true;
        } else {
            /* Find the Value and remove it */
            Node pre = head;
            Node cur = head.next;

            while(cur != null) {
                if(cur.val == val) {
                /* Found the value and remove it */
                pre.next = cur.next;
                this.currentNumber--;
                return true;
                }
                pre = pre.next;
                cur = cur.next;
            }

            return false;
        }
    }
    /* Linked List get Random takes O(n) time complexity */
    public int getRandom() {
        if(head == null) return 0;
        int randomSteps = (int) (Math.random() * (this.currentNumber));
        Node cur = head;
        for(int i = 0; i < randomSteps; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public int numberInTheSet() {
        return this.currentNumber;
    }

    public boolean isEmpty() {
        return this.currentNumber == 0;
    }
}



class Node {
    int val;
    Node next;
    public Node(int val) {
        this.val = val;
    }
}
