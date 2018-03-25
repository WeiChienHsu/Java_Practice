class Solution {
  public int getImportance(List<Employee> employees, int id) {
      
      Deque<Integer> queue = new ArrayDeque<>();
      int total = 0;
      
      queue.offerLast(id);
      // Find the "target Value" and put it's subordinates into Queue
      while(!queue.isEmpty()) {
          int targetId = queue.pollFirst();
          for(int i = 0; i < employees.size(); i++ ) {
              Employee curEmployee = employees.get(i);
              if(curEmployee.id == targetId) {
                  total += curEmployee.importance;
                  // Add its subordinates into Queue
                  for(int j = 0; j < curEmployee.subordinates.size(); j++ ){
                      queue.offerLast(curEmployee.subordinates.get(j));
                  }
                  break;
              }
          }
      }
      
      return total;
      
  }
}