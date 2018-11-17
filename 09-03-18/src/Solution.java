import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        String num = "123";
        int target = 0;
        List<String> res = addOperators(num, target);
        for(String str : res) {
            System.out.println(str);
        }
    }

    public static List<String> addOperators(String num, int target) {
        List<String> results = new ArrayList<>();
        if(num == null || num.length() == 0) return results;
        dfsHelper(results, "", num, target,0, 0);
        return results;
    }

    public static void dfsHelper(List<String> results,
                                 String path,
                                 String num,
                                 int target,
                                 int startIndex,
                                 int totalValue) {

        if(startIndex == num.length()) {
            if(target == totalValue) {
                results.add(path);
                return;
            }
        }

        for(int i = startIndex; i < num.length(); i++) {
            if(i != startIndex && num.charAt(startIndex) == '0'){
                break;
            }

            int currentValue = Integer.parseInt(num.substring(startIndex, i + 1));

            if(startIndex == 0) {
                dfsHelper(results, path + currentValue, num, target, i + 1, currentValue);
            } else {
                dfsHelper(results, path + "+" + currentValue, num ,target, i + 1, totalValue + currentValue);
                dfsHelper(results, path + "-" + currentValue, num ,target, i + 1, totalValue - currentValue);

            }
        }
    }
}
