import java.util.HashMap;
import java.util.Map;

public class longestConsecutive {
    public static void main(String[] args) {
        int[] a = {100, 4, 200, 1, 3, 2};
        System.out.println(longest(a));
    }

    public static int longest(int[] nums ) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int num : nums) {
            if(!map.containsKey(num)){
                int left = (map.containsKey(num - 1))? map.get(num - 1) : 0;
                int right = (map.containsKey(num +1))? map.get(num + 1) : 0;
                int sum = left + right + 1;
                map.put(num,sum);
                res = Math.max(res, sum);

                map.put(num - left, sum);
                map.put(num + right, sum);
            } else {
                continue;
            }
        }

        return res;
    }
}
