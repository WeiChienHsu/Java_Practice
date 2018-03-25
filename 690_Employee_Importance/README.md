# Employee Importance



## Solution

- Init Class
```java
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
```

- Analyze the Class
```java
Employee targetEmployee =  employees.get(i);
int curId = targetEmployee.id;
int curImportance = targetEmployee.importance;
List<Integer> subordinates = targetEmployee.subordinates;
```

- Used a Queue to record all subordinates or un-porcess employee
- Used a Int record Value variable to keep tracking the latest value
- For example, put the target id into Queue, and we go through the employees List to find that specific value, and keep adding it's subordinates into the Queue and add the value of it importance to out Record Variable

```java
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
```