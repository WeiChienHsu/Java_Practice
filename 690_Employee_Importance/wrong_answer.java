/* Didn't consider the Second Level  */
/*  [[1,5,[2,3]],[2,3,[4]],[3,4,[]],[4,1,[]]]  1 */

class Solution {
  public int getImportance(List<Employee> employees, int id) {
      
      Set<Integer> subId = new HashSet<>();
      int total = 0;
      
      // Find the target id and it Subordinates
      for(int i = 0; i < employees.size(); i++ ) {
          if(employees.get(i).id == id) {
              Employee targetEmployee = employees.get(i);
              total += targetEmployee.importance;
              // Put all subordinates into the map
              for(int j = 0; j < targetEmployee.subordinates.size(); j++) {
                  subId.add(targetEmployee.subordinates.get(j));
              }
          }
      }
      
      // Go Throught the List and add all importance of id who contains in the subIdSet
      for(int k = 0; k < employees.size(); k++) {
          Employee curEmployee = employees.get(k);
          if(subId.contains(curEmployee.id)){
              total += curEmployee.importance;
          }
      }
      
      return total;
      
  }
}