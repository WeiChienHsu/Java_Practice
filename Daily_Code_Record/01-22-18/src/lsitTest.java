import java.util.ArrayList;
import java.util.List;

public class lsitTest {
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list.add(0,list1);
        list.add(0,list2);

        System.out.println(list);

    }

}
