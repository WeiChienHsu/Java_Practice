import java.util.ArrayList;
import java.util.List;

public class twoSum {
    public static void main(String[] args) {
        Number num = new Number();
        num.lessThan(24);
    }
}

class Number{
    int[] nums = new int[4];
    public Number(){
        nums[0] = 2;
        nums[1] = 7;
        nums[2] = 11;
        nums[3] = 15;
    }

    public void combination(){
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        helper(nums, res, results, 0);

        for(int i = 0; i < results.size(); i++){
            System.out.println(results.get(i));
        }
    }

    private void helper(int[] nums, List<Integer> res, List<List<Integer>> results, int startIndex) {

        if(startIndex == nums.length) {
            results.add(new ArrayList<>(res));
        }

        for(int i = startIndex; i < nums.length; i++) {
            res.add(nums[i]);
            helper(nums, res, results, i+1);
            res.remove(res.size() - 1);
        }


    }

    public void lessThan(int target) {
        List<>
        int left = 0;
        int right = nums.length - 1;
        int count = 0;
        while (left < right){
            if(nums[left] + nums[right] < target) {
                count += right - left;
                left++;
            } else {
                right--;
            }
        }
        System.out.println("Number greater than " + target + " is " + count);
    }
}
