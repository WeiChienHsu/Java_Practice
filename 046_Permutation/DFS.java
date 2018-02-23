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

import java.util.ArrayList;
import java.util.List;

public class par {

    public static void main(String[] args) {
        int[] num = {1 , 2 , 3 , 4};
        System.out.println(permute(num));
    }

    public static List<List<Integer>> permute(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<>();
        dfs(num, list, res);
        return res;
    }

    public static void dfs(int[] num, List<Integer> list, List<List<Integer>> res) {
        if(list.size() == num.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }

        for(int i = 0; i < num.length; i++) {
            if(!list.contains(num[i])) {
                list.add(num[i]);
                dfs(num, list, res);
                list.remove(list.size()-1);
            }
        }
    }
}
