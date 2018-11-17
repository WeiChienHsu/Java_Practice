import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class testBinary {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5};
        List<Integer> list = new ArrayList<>();
        for(int num : nums)
            list.add(num);

        int num = 2;
        int idx = Collections.binarySearch(list, num);

        list.add(-(idx + 1), num);

        for(int n : list)
            System.out.println(n);
    }
}
