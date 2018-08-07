import java.util.ArrayList;
import java.util.List;

public class letterCombinations {

    public static void main(String[] args) {
        List<String> ans = getResult("234");
        for(int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i));
            System.out.print(" ");
        }

    }

    public static List<String> getResult(String digits) {
        List<String> result = new ArrayList<>();
        StringBuilder combination = new StringBuilder();

        dfsHelper(result, combination, 0, digits);
        return result;
    }

    public static void dfsHelper(List<String> result,
                                 StringBuilder combination,
                                 int index,
                                 String digits) {

        if(index == digits.length()){
            result.add(combination.toString());
            return;
        }

        String currentString = letterCombinations.getString(digits.charAt(index));

        for(int i = 0; i < currentString.length(); i++) {
            combination.append(currentString.charAt(i));
            letterCombinations.dfsHelper(result, combination, index + 1, digits);
            combination.deleteCharAt(combination.length() - 1);
        }
    }

    static public String getString(char num) {
        switch (num) {
            case '2':
                return "abc";
            case '3':
                return "def";
            case '4':
                return "ghi";
            case '5':
                return "jkl";
            case '6':
                return "mno";
            case '7':
                return "pqrs";
            case '8':
                return "tuv";
            case '9':
                return "wxyz";
        }


        return "";
    }
}


