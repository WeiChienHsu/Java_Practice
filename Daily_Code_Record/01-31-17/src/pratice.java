import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        // Type -> Accumulator -> Result
        List<String> nameList = employeeList.stream()
                .filter((e) -> e.getAge() >= 18)
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .map(e -> e.getName().toUpperCase())
                .collect(Collectors.toList());
        System.out.println(nameList);

        String employeeList2 = employeeList.stream()
                .filter((e) -> e.getAge() >= 18)
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .map(e -> e.getName().toUpperCase())
                .collect(Collectors.joining("#"));
        System.out.println(employeeList2);

        int ageSum = employeeList.stream().mapToInt(Employee::getAge).sum();
        System.out.println(ageSum);

        IntSummaryStatistics summary = employeeList.stream()
                .map(Employee::getAge)
                .collect(Collectors.summarizingInt(p -> p));
        System.out.println("Average Age: " + Math.floor(summary.getAverage()));
        System.out.println("How many people: " + summary.getCount());
        System.out.println("Max: " + summary.getMax());
        System.out.println("Min: " + summary.getMin());
        System.out.println("Sum: " + summary.getSum());
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