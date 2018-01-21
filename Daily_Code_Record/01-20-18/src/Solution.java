class Solution {

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World"));

    }
    public static int lengthOfLastWord(String s) {
        return s.trim().length() - s.trim().lastIndexOf(" ") - 1;
    }
}