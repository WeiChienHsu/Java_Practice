public class practice {

    public static void main(String[] args) {
        stream();

    }

    public static void stream(){
        Employee e1 = EmployeeFactor.create("Kevin", 22);
    }

    @FunctionalInterface
    private interface EmployeeFactor {
        Employee create(String name, int age);
    }

}



    class Employee {
        String name;
        int age;

        public Employee(String name, int age) {
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

