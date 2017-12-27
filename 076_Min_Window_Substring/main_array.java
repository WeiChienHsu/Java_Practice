public class minimumSubstring_array {

    public static void main(String[] args) {
        String s = "ABDGCEBANC";
        String t = "BAC";
        System.out.println(minWindow(s,t));
    }

    public static String minWindow(String s, String t) {
        int[] map = new int[128];

        int start = 0;
        int end = 0;
        int count = t.length();
        int minLen = Integer.MAX_VALUE;
        int startIndex = 0; // Record the index to return match String

        // Add all T character into map array [ index: A, exist number ]
        for(char c: t.toCharArray()) {
            map[c]++;
        }

        char[] chS = s.toCharArray();


        while(end < chS.length) {
           if(map[chS[end++]]-- > 0) {
               count--;
           }

           while(count == 0) {
               if(end - start < minLen) {
                   startIndex = start;
                   minLen = end - start;
               }

               if(map[chS[start++]]++ == 0){
                   count++;
               }
           }
        }
        return minLen == chS.length ? new String() : new String(chS, startIndex, minLen);
    }
}
