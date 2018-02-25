import java.util.ArrayList;
import java.util.List;

public class subStringTest {
    public static void main(String[] args) {
        String s = "aac";
        System.out.println(partition(s));

    }

    public static List<List<String>> partition(String s) {
        List<List<String>> results = new ArrayList<>();
        if(s.length() == 0 ) {
            return results;
        }

        List<String> combination = new ArrayList<>();
        dfsHelper(results, combination, 0, s);
        return results;
    }

    private static void dfsHelper(List<List<String >> results,
                                  List<String> combination,
                                  int startIndex,
                                  String s) {
        if(s.length() == startIndex) {
            results.add(new ArrayList<>(combination));
            return;
        }

        for(int i = startIndex; i < s.length(); i++) {
            if(isPart(s,startIndex,i)){
                combination.add(s.substring(startIndex,i + 1));
                dfsHelper(results,combination, i + 1, s);
                combination.remove(combination.size() - 1);
            }


        }
    }

    private static boolean isPart(String s , int low, int high) {
        while (low < high) {
            if(s.charAt(low++) != s.charAt(high--)){
                return false;
            }
        }
        return true;
    }
}
