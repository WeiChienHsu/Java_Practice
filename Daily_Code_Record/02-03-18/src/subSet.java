import java.util.ArrayList;
import java.util.List;

public class subSet {
    public static void main(String[] args) {
        int[] nums = {2,3,4};
        System.out.println(findSet(nums));
    }

    public static List<List<Integer>> findSet(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for(int i = 0; i < separate(nums).size(); i++ ){
            for(int j = 0; j < separate(nums).get(i).size(); j++ ) {
                res.add(separate(nums).get(i));
            }
        }
    }

    public static List<List<Integer>> separate(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for(int i = 0; i < nums.length; i++ ) {
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < nums.length; j++) {
                if(nums[j] != nums[i]) {
                    list.add(nums[j]);
                }
            }
            res.add(list);
        }
        return res;
    }
}
