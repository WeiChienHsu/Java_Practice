import java.util.ArrayList;
import java.util.List;

public class permutations1 {
    public static void main(String[] args) {
        int[] a = new int[] {1,2,3};
        System.out.println(permute(a));

    }

    public static List<List<Integer>> permute(int[] nums) {
        // Corner Case Check
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<>();
        helper(nums, list, res);
        return res;
    }

    public static void helper(int nums[], List<Integer> list,List<List<Integer>> res ){
        if(list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if(!list.contains(nums[i])){
                list.add(nums[i]);
                helper(nums, list, res);
                list.remove(list.size()-1);
            }
        }
    }

}
