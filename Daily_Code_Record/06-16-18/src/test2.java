import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class test2 {
    public static void main(String[] args) {
//        String[] s = {"eat", "tea", "tan", "ate", "nat", "bat"};
//        List<List<String>> result = new ArrayList<>();
//        Map< char[], List<String>> map = new HashMap<>();
//        for(int i = 0; i < s.length; i++) {
//            String currentString = s[i];
//            char[] currentCharArray = currentString.toCharArray();
//            if(!map.containsKey(currentCharArray)) {
//                map.put(currentCharArray, new ArrayList<String>());
//                map.get(currentCharArray).add(currentString);
//            } else {
//                map.get(currentCharArray).add(currentString);
//            }
//        }
//
//        for(char[] c : map.keySet()){
//            result.add(map.get(c));
//        }

        char[] c1 = {'a', 'b', 'c'};
        char[] c2 = {'b', 'a', 'c'};
        Arrays.sort(c2);

        String s1 = String.valueOf(c1);
        String s2 = String.valueOf(c2);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        String[] s = new String[2];
        s[0] = s1;
        s[1] = s2;

        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < 2; i++) {
            map.put(s[i], map.getOrDefault(s[i], 0) + 1);
        }

        for(String key : map.keySet()) {
            System.out.print("Key: ");
            System.out.print(key);
        }

    }
}
