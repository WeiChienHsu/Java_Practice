import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class par2 {

    public static void main(String[] args) {
        int[] num = {1 , 1 , 2};
        System.out.println(permuteUnique(num));
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        dfs(nums, res, 0);
        return res;
    }

    public static void dfs(int[] nums, List<List<Integer>> res, int pos) {
        if (pos == nums.length) {
            List<Integer> list = new ArrayList<>();
            for(int num : nums) {
                list.add(num);
            }
            res.add(list);
            return;
        }

        Set<Integer> used = new HashSet<>(); // To check the first number
        for(int i = pos; i < nums.length; i++) {
            if(used.add(nums[i])) {
                swap(nums, i, pos);
                dfs(nums, res, pos +1);
                swap(nums, i , pos);
            }
        }
    }

    public static void swap(int[] nums, int i, int p) {
            int temp = nums[i];
            nums[i] = nums[p];
            nums[p] = temp;
        }


    }



