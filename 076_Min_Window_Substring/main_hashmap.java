import java.util.HashMap;
import java.util.Map;

public class minimunSubstring {

    public static void main(String[] args) {
        String s = "ABDGCEBANC";
        String t = "BAC";
        System.out.println(minWindow(s,t));
    }

    public static String minWindow(String s, String t) {
        Map<Character, Integer> frequency = new HashMap<>();

        // Put all characters in T into HashMap

        char[] chS = s.toCharArray();
        for(char c: t.toCharArray()) {
            Integer freq = frequency.get(c);
            if(freq == null) {
                frequency.put(c, 1);
            } else {
                frequency.put(c, freq + 1); // if char appears not only one time
            }
        }

        int start = 0;
        int end = 0;
        int count = t.length();
        int minLen = s.length();
        int startIndex = -1; // Record the index to return match String

        while(end < chS.length) {
            Integer freq = frequency.get(chS[end]);
            // freq == null means its not in T

            if(freq != null) {
                frequency.put(chS[end], freq -1);
                if(freq > 0){ // in case of over match
                    count--;
                }
            }
            // Move end
            end++;

            // Satisfied with all number between two pointers
            while(count == 0) {
                if(end - start < minLen) {
                    minLen = end - start;
                    startIndex = start;
                }

                // If match, check if start pointer is on the target number
                Integer exist = frequency.get(chS[start]);
                if(exist != null) {
                    frequency.put(chS[start], exist + 1);
                    if(exist == 0) { // in case of over match
                        count ++;
                    }
                }
                // Move right
                start++;
            }
        }
        return minLen == chS.length ? new String() : new String(chS, startIndex, minLen);
    }
}
