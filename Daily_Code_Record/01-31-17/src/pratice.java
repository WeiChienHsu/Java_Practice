import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class pratice {
    public static void main(String[] args) {
        EmployeeFactory employeeFactory = Employee::new;
        Employee e1 = employeeFactory.create("Kevin", 12);
        Employee e2 = employeeFactory.create("John", 22);
        Employee e3 = employeeFactory.create("Peter", 33);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);
        employeeList.sort((o1, o2) -> o1.getAge() - o2.getAge());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < employeeList.size(); i++) {
            Employee emp = employeeList.get(i);
            if(emp.getAge() >= 18) {
                sb.append(emp.getName().toUpperCase());
                 if(i != employeeList.size() - 1) {
                sb.append(" # ");
                }
            }
        }
        System.out.println(sb.toString());


        Optional<String> longName = employeeList.stream()
                .filter((e) -> e.getAge() >= 18)
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .map(e -> e.getName().toUpperCase())
                .reduce((o1, o2) -> o1 + " # " + o2);
        System.out.println(longName.get());
    }
}

@FunctionalInterface
interface EmployeeFactory{
    Employee create(String name, int age);
}

class Employee {
    private String name;
    private int age;
    Employee(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}