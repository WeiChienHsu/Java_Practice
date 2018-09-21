import java.util.*;

public class substringK {
    public static void main(String[] args) {
        String s = "aadfsb";
        int K = 2;
        List<String> list = uniqueK(s, K);

        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        int result = uniqueKCount(s, K);
        System.out.println(result);

    }

    public static List<String> uniqueK(String str, int k) {
        List<String> results = new ArrayList<>();
        int len = str.length();

        /* To record the count of characters from 'a' to 'z' */
        int map[] = new int[26];

        /* Start from the first character in the String */
        /* Get all substrings which count as K unique characters */
        for(int i = 0; i < len; i++) {
            int count = 0;
            Arrays.fill(map, 0);

            for(int j = i; j < len; j++) {
                /* Find a new Character */
                if(map[str.charAt(j) - 'a'] == 0) {
                    count++;
                }
                /* Character existed */
                map[str.charAt(j) - 'a'] ++;

                if(count == k) {
                    results.add(str.substring(i, j + 1));
                }
            }
        }

        return results;
    }

    public static List<String> uniqueKDeduplicated(String str, int k) {
        List<String> results = new ArrayList<>();
        int len = str.length();

        /* To record the count of characters from 'a' to 'z' */
        int map[] = new int[26];
        Set<String> set = new HashSet<>();

        /* Start from the first character in the String */
        /* Get all substrings which count as K unique characters */
        for(int i = 0; i < len; i++) {
            int count = 0;
            Arrays.fill(map, 0);

            for(int j = i; j < len; j++) {
                /* Find a new Character */
                if(map[str.charAt(j) - 'a'] == 0) {
                    count++;
                }
                /* Character existed */
                map[str.charAt(j) - 'a'] ++;

                if(count == k) {
                    if(!set.contains(str.substring(i, j + 1))) {
                        results.add(str.substring(i, j + 1));
                        set.add(str.substring(i, j+ 1));
                    }

                }
            }
        }

        return results;
    }

    public static int uniqueKCount(String str, int k) {
        int result = 0;
        int len = str.length();

        /* To record the count of characters from 'a' to 'z' */
        int map[] = new int[26];

        /* Start from the first character in the String */
        /* Get all substrings which count as K unique characters */
        for(int i = 0; i < len; i++) {
            int count = 0;
            Arrays.fill(map, 0);

            for(int j = i; j < len; j++) {
                /* Find a new Character */
                if(map[str.charAt(j) - 'a'] == 0) {
                    count++;
                }
                /* Character existed */
                map[str.charAt(j) - 'a'] ++;

                if(count == k) {
                    result ++;
                }
            }
        }

        return result;
    }
}
