import java.util.ArrayList;
import java.util.List;

public class factor_Combination {

    public static void main(String[] args) {
        System.out.println(getFactors(12));
    }

    public static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<>();
        treeWithPrune(res, list, n ,2);
        return res;
    }

    private static void treeWithPrune(List<List<Integer>> res, List<Integer> list, int n, int startFactor) {
        if(n == 1) {
            if(list.size() > 1) {
                res.add(new ArrayList<>(list));
            }
            return;
        }

        for(int i = startFactor ; i <= n ; i++) {
            if(n % i == 0) {
                list.add(i);
                treeWithPrune(res,list,n/i, i);
                list.remove(list.size() - 1);
            }
        }
    }
}
