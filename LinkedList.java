public static void main (String[] args) {
    // head = 1->2->3
    // constructe linkedlist
    Node head = new Node(1);
    head.next = new Node(2);  
    head.next.next = new Node(3);
    
    // print linkedlist
    Node reversedList = reverse(head);
    Node cur = reversedList;
    
    while(cur != null) {
      System.out.println (cur.val);
      cur = cur.next;
    }
    
    
    
  }
  
  public static Node reverse(Node head) {

    Node cur = head;
    Node pre = null;
    
    while(cur != null) {

      Node tmp = cur.next;
      cur.next = pre;
      pre = cur;
      cur = tmp;
    }
    
    return pre;
  }
}

// 1->2->3



