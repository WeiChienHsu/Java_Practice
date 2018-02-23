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
            String subString = s.substring(startIndex, i + 1);
            if(!isPart(subString)){
                continue;
            }
            combination.add(subString);
            dfsHelper(results,combination, i + 1, s);
            combination.remove(combination.size() - 1);
        }
    }

    private static boolean isPart(String s) {
        for(int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
