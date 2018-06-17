import java.util.*;

public class test {
    public static void main(String[] args) {
        String S = "eccbbbbdec";


        Map<Character, List<Integer>> map = new LinkedHashMap<>();
        for(int i = 0; i < S.length(); i++) {
            char n = S.charAt(i);
            if(!map.containsKey(n)) map.put(n, new ArrayList<Integer>());
            List<Integer> list = map.get(n);

            // Add number into the list
            if(list.size() < 2) {
                list.add(i);
                list.add(i);
            } else {
                list.set(1, i);
            }
        }

        List<Integer> res = new ArrayList<>();
        int oldStart = -1;
        int oldEnd = -1;
        int count = 0;

        for(char n : map.keySet()) {
            System.out.println(n);
            int newStart = map.get(n).get(0);
            int newEnd = map.get(n).get(1);

            if(oldStart == -1 && oldEnd == -1) {
                oldStart = newStart;
                oldEnd = newEnd;
            }

            if(newStart > oldEnd) {
                res.add(oldEnd - oldStart + 1);
                oldEnd = newEnd;
                oldStart = newStart;
            } else {
                if(newEnd > oldEnd) {
                    oldEnd = newEnd;
                }
            }
        }
        System.out.print(oldStart);
        System.out.print(oldEnd);
        res.add(oldEnd - oldStart + 1);

        for(int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i));
            System.out.print(" ");
        }

//        for(char n : map.keySet()) {
//            System.out.print(n);
//            System.out.print(" : ");
//            System.out.print(" start - ");
//            System.out.print(map.get(n).get(0));
//            System.out.print(" end - ");
//            System.out.println(map.get(n).get(1));
//            System.out.println("----");
//        }

//        Map<Character, Integer> map = new HashMap<>();

//        for(int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            map.put(c, map.getOrDefault(c, 0) + 1);
//        }
//
//        for(char n : map.keySet()) {
//            System.out.print(n);
//            System.out.print(" : ");
//            System.out.println(map.get(n));
//        }
//        System.out.print(s.length());
    }
}


