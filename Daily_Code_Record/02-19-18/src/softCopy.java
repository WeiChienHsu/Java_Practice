import java.util.ArrayList;
import java.util.List;

public class softCopy {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = list1;
        list1.add(1);
        System.out.println(list2);
        List<Integer> list3 = new ArrayList<>(list1);
        list1.add(1);
        System.out.println(list2);
        System.out.println(list3);
    }
}
