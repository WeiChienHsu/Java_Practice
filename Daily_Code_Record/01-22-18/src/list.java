import java.util.ArrayList;
import java.util.List;

public class list {
    public static void main(String[] args) {

        List<List<Integer>> listTest = new ArrayList<List<Integer>>();
        for(int i = 0; i < 5; i++) {
            List<Integer> a = new ArrayList<>();
            listTest.add(a);
        }

        listTest.get(1).size();

    }
}
