public class copy {
    public static void main(String[] args) {
        int x = 7;
        System.out.println("Outside the methods: X = " + x);
        foo(x);
        System.out.println("Outside the methods(After call method): X = " + x);

    }
    public static void foo(int x) {
        x = 10;
        System.out.println("Inside the method: X = " + x);
    }
}

