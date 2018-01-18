import java.util.ArrayList;
import java.util.List;

public class getFactor {

    public static void main(String[] args) {
        int n = 2400;
        System.out.println(getFactors(n));
    }

    public static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<>();
        helper(res, list, n, 2);
        return res;

    }

    public static void helper(List<List<Integer>> res, List<Integer> list, int n, int firstFactor) {
        if(n == 1) {
            if(list.size() > 1) {
                res.add(new ArrayList<Integer>(list));
            }
            return;
        }

        for(int i = firstFactor; i <= Math.sqrt(n); i++){
            if(n % i == 0) {
                list.add(i);
                helper(res, list, n/i, i);
                list.remove(list.size()-1);
            }
        }

        list.add(n);
        helper(res,list,1,n);
        list.remove(list.size()-1);
    }
}
