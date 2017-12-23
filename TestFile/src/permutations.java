import java.util.ArrayList;
import java.util.List;

public class permutations {
    public static void main(String[] args) {
        int[] a = new int[] {1,2,3};
        System.out.println(permute(a));

    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0) return res;
        res.add(new ArrayList<Integer>());

        for(int i = 0; i < nums.length; i++) {
            // Next level
            List<List<Integer>> nextRes = new ArrayList<List<Integer>>();
            // for each list in res
            for(List<Integer> list : res) {
                for(int j = 0; j < list.size() + 1; j++ ){
                    // Copy a list to nextList
                    List<Integer> nextList = new ArrayList<>(list);
                    // Add each position in list
                    nextList.add(j,nums[i]);
                    nextRes.add(nextList);
                }
            }
            // Move to next level
            res  = nextRes;
        }
        return res;
    }

    public static class permutations2 {
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

        public static void swap(int[] nums, int pos, int i) {
            int temp = nums[i];
            nums[i] = nums[pos];
            nums[pos] = temp;
        }

    }
}

