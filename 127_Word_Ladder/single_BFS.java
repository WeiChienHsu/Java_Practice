import java.util.*;

public class ladder {

    public static void main(String[] args) {
        String start = "hit";
        String end = "cog";
        String[] strs = {"hot","dot","dog","lot","log"};
        List<String> wordList = new ArrayList<>();
        wordList = Arrays.asList(strs) ;
        System.out.println(ladderLength(start, end, wordList));
    }


    public static int ladderLength(String start, String end, List<String> wordList) {
        if(start == null || end == null || wordList == null) return 0;
        if(wordList.size() == 0) return 0;

        return bfsHelper(start, end, wordList);
    }

    private static int bfsHelper(String start, String end, List<String> wordList) {
        Deque<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        int steps = 0;

        queue.offerLast(start);
        visited.add(start);

        while(!queue.isEmpty()) {
            int size = queue.size(); // In same level
            steps++;
            for(int i = 0 ; i < size; i++) {
                String curStr = queue.pollFirst();

                for(int j = 0; j < curStr.length(); j++) {
                    for(char k = 'a'; k <= 'z'; k++) {
                        String toStr = replace(curStr, j, k);

                        if(toStr.equals(end)) {
                            return ++steps;
                        }
                        // Add to visited set and queue if it's in wordList
                        if(visited.add(toStr) && wordList.contains(toStr)) {
                            queue.offerLast(toStr);
                        }
                    }
                }

            }
        }
        return 0;
    }

    // Replace each Character in String
    private static String replace(String str, int i, char c) {
        char[] chs = str.toCharArray();
        chs[i] = c;
        return new String(chs);
    }

}
