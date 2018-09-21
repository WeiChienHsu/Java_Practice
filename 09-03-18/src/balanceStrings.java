public class balanceStrings {
    public static void main(String[] args) {
        String s = "acdbddbbbbaaac";
        System.out.println(checkBalanced(s));

    }

    public static boolean checkBalanced(String str) {
        int[] characters = new int[4]; /* a = 0, b = 1, c = 2, d = 3 */
        for(char c : str.toCharArray()) {
            characters[c - 'a']++;
        }
        return ((characters[0] + characters[2]) % 2 == 0)
                && ((characters[1] + characters[3]) % 2 == 0);
    }
}
