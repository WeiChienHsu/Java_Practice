public class lambda {
    public static void main(String[] args) {

        practice.EmployeeFactor employeeFactor = (name, age) -> new practice.Employee(name, age);
        practice.Employee e1 = employeeFactor.create("Kevin", 16);
        System.out.println(e1.getName());


    }
}

