class Solution {
  // [h, k] 前方有 k 個， >= h 的人
  // 1. 先使用 Comparator 來排序，第一判斷條件是身高（從大到小 b[0] - a[0]，若身高相同，判斷後者數字(從小到大 a[1] -  b[1])
  // 2. 自製一個 LinkedList 來處理 insert
  // 3. 插入函數：處理三種狀況 rank == 0, rank > 1, rank == 1
  
  public int[][] reconstructQueue(int[][] people) {
      if(people.length == 0) return people;
      int[][] results = new int[people.length][people[0].length];
      // Sort the original array by it's height and if it is the same
      // sorted by the rank(second element in the list)
      Arrays.sort(people, new Comparator<int[]>(){
         @Override
          public int compare(int[] a, int[] b) {
              if(a[0] != b[0]) return b[0] - a[0];
              else return a[1] - b[1];
          }
      });
      
      // Init the original Node
      Node originalNode = new Node(people[0]);
      
      // Put all person into the Node
      for(int i = 1; i < people.length; i++) {
          originalNode = Solution.insertNode(originalNode, people[i]);
      }
      
      // Put all person into the result array
      for(int i = 0; i < people.length; i++) {
          results[i] = originalNode.person;
          originalNode = originalNode.next;
      }
      return results;
  }
  
  // rank == 0: insert the newNode in front or original Node and return newNode
  // rank > 1: find the current node that makes the rank == 1 by pointing to the next current node
  // rank == 1: insert the newNode into the next one from the current node and points to the original current next
  
  public static Node insertNode(Node originalNode, int[] person) {
      // Given a new node
      Node newNode = new Node(person);
      int rank = person[1];
      if(rank == 0) {
          newNode.next = originalNode;
          return newNode;
      }
      
      // Given a current pointer to handle connection
      Node current = originalNode;
      while(rank > 1) {
          current = current.next;
          rank--;
      }
      
      // Insert the newNode to the current.next
      // connect current.next with newNode.next
      Node next = current.next;
      newNode.next = next;
      current.next = newNode;
      return originalNode;     
  }
}

class Node {
  public int[] person;
  public Node next;
  public Node(int[] p) {
      this.person = p;
  }
}
