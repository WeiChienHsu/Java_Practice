public class Solution {

    public static void main(String[] args) {
        String s = " l";
        if("blue is sky the".equals(reverseWords(s))){
            System.out.println("Correct");
        } else {
            System.out.println(reverseWords(s));
        }
    }

    public static String reverseWords(String s) {
        String[] strings = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = strings.length - 1; i >= 0; i--) {
            if(i == 0) {
                sb.append(strings[i]);
                break;
            }
            sb.append(strings[i] + " ");
        }
        return sb.toString();
    }
}
