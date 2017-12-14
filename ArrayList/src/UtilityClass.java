import java.util.ArrayList;

public class UtilityClass {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(10);
        a.add(10 >> 1);
        a.add(21);
        System.out.println(a.get(1));
        a.remove(2);

    }
}
