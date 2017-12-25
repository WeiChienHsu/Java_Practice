import java.util.HashMap;
import java.util.Map;

public class TwoSumII {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int count = 0;
        int[] result = twoSum(nums, target);
        for(int i = 0; i < result.length; i ++){
            System.out.println(result[i]);
        }

    }

    public static int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0 ; i < numbers.length; i++ ) {
            if(map.containsKey(target - numbers[i]))  {
                result[0] = map.get(target - numbers[i]);
                result[1] = i + 1 ;
            } else {
                map.put(numbers[i], i + 1);
            }
        }
        return result;

    }

}
