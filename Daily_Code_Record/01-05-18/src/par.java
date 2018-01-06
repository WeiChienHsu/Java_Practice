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
