public class reorderList {

    public static void main(String[] args) {
        Node node = new Node<Integer>(1);


    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;
        Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

}
