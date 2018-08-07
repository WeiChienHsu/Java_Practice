import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        List<String> ans = generateParenthesis(3);
        for(int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i));
            System.out.print(" ");
        }
    }

    public static List<String> generateParenthesis(int num) {
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();
        dfsHelper(result, 0, 0, num, sb);
        return result;
    }

    public static void dfsHelper(List<String> result, int left, int right, int num, StringBuilder sb){
        if(right > left) return;

        if(left != num) {
            sb.append("(");
            dfsHelper(result, left + 1, right, num, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        if(right != num) {
            sb.append(")");
            dfsHelper(result, left, right + 1, num, sb);
            sb.deleteCharAt(sb.length() - 1);   
        }

        if(right == num && left == num) {
            result.add(sb.toString());
            return;
        }
    }

}

