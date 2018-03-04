public class stringTest {

    public static void main(String[] args) {
        String s = "0P";
        System.out.println(isPalindrome(s));
    }

    private static boolean isPalindrome(String s){
        s = s.toLowerCase().trim();
        int start = 0;
        int end = s.length() - 1;
        System.out.println(s);
        char cHead, cTail;
        while(start <= end) {
            cHead = s.charAt(start);
            cTail = s.charAt(end);
            if(!Character.isLetterOrDigit(cHead)) {
                start++;
            } else if (!Character.isLetterOrDigit(cTail)) {
                end--;
            } else {
                if(cHead != cTail) {
                    return false;
                }
                start++;
                end--;
            }
        }
        return true;
    }
}
