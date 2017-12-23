import java.util.ArrayList;
import java.util.List;

public class permutations2 {
    public static void main(String[] args) {
        int[] a = new int[] {1,2,3};
        System.out.println(permute(a));
    }

    public static List<List<Integer>> permute(int[] nums) {
// Corner Case Check
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0) return res;

        List<Integer> list = new ArrayList<>();
        helper(nums, list, res, 0);
        return res;
    }

    public static void helper(int[] nums, List<Integer> list,List<List<Integer>> res, int pos ){
        if(pos == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }

        for(int i = pos; i < nums.length; i++) {
            list.add(nums[i]);
            swap(nums, pos, i);
            helper(nums, list, res, pos + 1);
            swap(nums, pos, i);
            list.remove(list.size() - 1);
        }
    }
    public static void swap(int[]nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }


}

